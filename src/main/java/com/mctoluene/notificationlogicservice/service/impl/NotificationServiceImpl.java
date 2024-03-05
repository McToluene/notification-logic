package com.mctoluene.notificationlogicservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mctoluene.notificationlogicservice.factory.processor.NotificationProcessorFactory;
import com.mctoluene.notificationlogicservice.helpers.NotificationIntegrationLogHelper;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.model.request.SmsWebhookDto;
import com.mctoluene.notificationlogicservice.model.response.NotificationResponse;
import com.mctoluene.notificationlogicservice.service.NotificationService;
import com.mctoluene.notificationlogicservice.service.TracingService;
import com.mctoluene.notificationlogicservice.service.internal.NotificationIntegrationLogInternalService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final NotificationProcessorFactory notificationProcessorFactory;
    private final NotificationIntegrationLogInternalService notificationIntegrationLogInternalService;
    private final TracingService tracingService;

    public NotificationServiceImpl(
            @Qualifier("notification-process") NotificationProcessorFactory notificationProcessorFactory,
            NotificationIntegrationLogInternalService notificationIntegrationLogInternalService,
            TracingService tracingService) {
        this.notificationProcessorFactory = notificationProcessorFactory;
        this.tracingService = tracingService;
        this.notificationIntegrationLogInternalService = notificationIntegrationLogInternalService;
    }

    @Override
    public void initiate(String payload) {
        try {
            log.info("Notification initiated");
            ObjectMapper objectMapper = new ObjectMapper();
            Notification notification = objectMapper.readValue(payload, Notification.class);
            if (notification.traceId() != null)
                tracingService.propagateSleuthFields(notification.traceId().toString());

            notificationIntegrationLogInternalService
                    .save(NotificationIntegrationLogHelper.buildIntegrationLog(payload, notification.channelName(),
                            notification.providerName()))
                    .map(notificationLog -> {
                        notificationProcessorFactory.process(notification, notificationLog.getId());
                        return Mono.empty();
                    })
                    .subscribe();

        } catch (JsonProcessingException exception) {
            log.error("Failed to convert payload", exception);
        }
    }

    @Override
    public void saveResponse(String payload) {
        try {
            log.info("Notification response");
            ObjectMapper objectMapper = new ObjectMapper();
            NotificationResponse notificationResponse = objectMapper.readValue(payload, NotificationResponse.class);
            notificationIntegrationLogInternalService.update(notificationResponse).subscribe();
        } catch (JsonProcessingException exception) {
            log.error("Failed to response payload", exception);
        }
    }

    @Override
    public void processCallback(String payload) {
        try {
            log.info("Callback initiated");
            ObjectMapper objectMapper = new ObjectMapper();
            SmsWebhookDto webhookEvent = objectMapper.readValue(payload, SmsWebhookDto.class);
            notificationProcessorFactory.processCallback(webhookEvent);
        } catch (JsonProcessingException exception) {
            log.error("Failed to convert payload", exception);
        }
    }
}
