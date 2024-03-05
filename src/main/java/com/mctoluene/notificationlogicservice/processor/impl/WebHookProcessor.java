package com.mctoluene.notificationlogicservice.processor.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.factory.provider.NotificationProviderFactory;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.processor.NotificationProcessor;

@Service
@Qualifier("WebHookProcessor")
public class WebHookProcessor implements NotificationProcessor {
    private final NotificationProviderFactory providerFactory;

    public WebHookProcessor(@Qualifier("WebHookProvider") NotificationProviderFactory providerFactory) {
        this.providerFactory = providerFactory;
    }

    @Override
    public void process(Notification notification, UUID notificationLogId) {
        providerFactory.getNotificationProvider(notification, notificationLogId);
    }
}
