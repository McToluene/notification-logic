package com.mctoluene.notificationlogicservice.processor;

import java.util.UUID;

import com.mctoluene.notificationlogicservice.model.Notification;

@FunctionalInterface
public interface NotificationProcessor {
    void process(Notification notification, UUID notificationLogId);
}
