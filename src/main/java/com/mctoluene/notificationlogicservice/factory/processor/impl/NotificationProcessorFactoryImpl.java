package com.mctoluene.notificationlogicservice.factory.processor.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mctoluene.notificationlogicservice.enums.NotificationChannel;
import com.mctoluene.notificationlogicservice.factory.processor.NotificationProcessorFactory;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.model.request.SmsWebhookDto;
import com.mctoluene.notificationlogicservice.processor.NotificationProcessor;
import com.mctoluene.notificationlogicservice.service.internal.NotificationIntegrationLogInternalService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@Qualifier("notification-process")
public class NotificationProcessorFactoryImpl implements NotificationProcessorFactory {
    private final Map<NotificationChannel, NotificationProcessor> processorMap;
    private final NotificationIntegrationLogInternalService notificationIntegrationLogInternalService;

    public NotificationProcessorFactoryImpl(@Qualifier("SMSProcessor") NotificationProcessor smsProcessor,
            @Qualifier("EmailProcessor") NotificationProcessor emailProcessor,
            @Qualifier("WebHookProcessor") NotificationProcessor webhookProcessor,
            NotificationIntegrationLogInternalService notificationIntegrationLogInternalService) {
        processorMap = new EnumMap<>(NotificationChannel.class);
        processorMap.put(NotificationChannel.SMS, smsProcessor);
        processorMap.put(NotificationChannel.EMAIL, emailProcessor);
        processorMap.put(NotificationChannel.WEBHOOK, webhookProcessor);
        this.notificationIntegrationLogInternalService = notificationIntegrationLogInternalService;
    }

    @Override
    public void process(Notification notification, UUID notificationLogId) {
        try {
            if (notification != null && !notification.channelName().isEmpty()) {
                var notificationChannel = NotificationChannel.valueOf(notification.channelName().toUpperCase());
                processorMap.get(notificationChannel).process(notification, notificationLogId);
            }
        } catch (IllegalArgumentException exception) {
            log.error("Failed to get channel", exception);
        }
    }

    @Override
    public void processCallback(SmsWebhookDto smsWebhookDto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String callbackResponseString = mapper.writeValueAsString(smsWebhookDto.originalPayload());

            notificationIntegrationLogInternalService.findByReferenceId(smsWebhookDto.id())
                    .map(notificationLog -> {
                        notificationLog.setCallbackResponse(callbackResponseString);
                        return notificationIntegrationLogInternalService.save(notificationLog);
                    })
                    .subscribe();

        } catch (IllegalArgumentException exception) {
            log.error("Failed to get provider", exception);
        } catch (JsonProcessingException exception) {
            log.error("Failed to convert callback response", exception);
            exception.printStackTrace();
        }
    }
}
