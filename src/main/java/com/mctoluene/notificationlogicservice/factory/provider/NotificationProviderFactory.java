package com.mctoluene.notificationlogicservice.factory.provider;

import java.util.UUID;

import com.mctoluene.notificationlogicservice.model.Notification;

@FunctionalInterface
public interface NotificationProviderFactory {
    void getNotificationProvider(Notification notification, UUID notificationLogId);
}
