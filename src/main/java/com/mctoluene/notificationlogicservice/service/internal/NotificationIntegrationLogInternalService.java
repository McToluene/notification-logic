package com.mctoluene.notificationlogicservice.service.internal;

import com.mctoluene.notificationlogicservice.domain.NotificationIntegrationLog;
import com.mctoluene.notificationlogicservice.model.response.NotificationResponse;

import reactor.core.publisher.Mono;

public interface NotificationIntegrationLogInternalService {
    Mono<NotificationIntegrationLog> save(NotificationIntegrationLog notificationIntegrationLog);

    Mono<NotificationIntegrationLog> update(NotificationResponse notificationIntegrationLog);

    Mono<NotificationIntegrationLog> findByReferenceId(String referenceId);
}
