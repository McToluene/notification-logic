package com.mctoluene.notificationlogicservice.provider;

import java.util.UUID;

import com.mctoluene.notificationlogicservice.model.Notification;

@FunctionalInterface
public interface ProviderSelector {
    void select(Notification notification, UUID notificationLogId);
}
