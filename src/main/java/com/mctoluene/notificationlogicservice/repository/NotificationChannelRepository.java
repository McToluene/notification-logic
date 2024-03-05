package com.mctoluene.notificationlogicservice.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.mctoluene.notificationlogicservice.domain.NotificationChannel;

public interface NotificationChannelRepository extends R2dbcRepository<NotificationChannel, UUID> {

}
