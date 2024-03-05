package com.mctoluene.notificationlogicservice.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.mctoluene.notificationlogicservice.domain.NotificationIntegrationLog;

import reactor.core.publisher.Mono;

public interface NotificationIntegrationLogRepository extends R2dbcRepository<NotificationIntegrationLog, UUID> {
    Mono<NotificationIntegrationLog> findByReferenceId(String referenceId);
}
