package com.mctoluene.notificationlogicservice.service.internal.impl;

import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.domain.NotificationIntegrationLog;
import com.mctoluene.notificationlogicservice.model.response.NotificationResponse;
import com.mctoluene.notificationlogicservice.repository.NotificationIntegrationLogRepository;
import com.mctoluene.notificationlogicservice.service.internal.NotificationIntegrationLogInternalService;

import reactor.core.publisher.Mono;

@Service
public class NotificationIntegrationLogInternalServiceImpl implements NotificationIntegrationLogInternalService {
    private final NotificationIntegrationLogRepository notificationIntegrationLogRepository;

    public NotificationIntegrationLogInternalServiceImpl(
            NotificationIntegrationLogRepository notificationIntegrationLogRepository) {
        this.notificationIntegrationLogRepository = notificationIntegrationLogRepository;
    }

    @Override
    public Mono<NotificationIntegrationLog> save(NotificationIntegrationLog notificationIntegrationLog) {
        return notificationIntegrationLogRepository.save(notificationIntegrationLog);
    }

    @Override
    public Mono<NotificationIntegrationLog> update(NotificationResponse notificationResponse) {
        return notificationIntegrationLogRepository.findById(notificationResponse.getNotificationLogId())
                .flatMap(notificationLog -> {
                    notificationLog.setResponseBody(notificationResponse.getResponseObject());
                    notificationLog.setReferenceId(notificationResponse.getReferenceId());

                    if ("success".equalsIgnoreCase(notificationResponse.getStatus()))
                        notificationLog.setStatus("Sent");
                    else
                        notificationLog.setStatus(notificationResponse.getStatus());

                    return notificationIntegrationLogRepository.save(notificationLog);
                });
    }

    @Override
    public Mono<NotificationIntegrationLog> findByReferenceId(String referenceId) {
        return notificationIntegrationLogRepository.findByReferenceId(referenceId);
    }

}
