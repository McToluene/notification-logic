package com.mctoluene.notificationlogicservice.processor.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.factory.provider.NotificationProviderFactory;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.processor.NotificationProcessor;

@Service
@Qualifier("EmailProcessor")
public class EmailProcessor implements NotificationProcessor {
    private final NotificationProviderFactory providerFactory;

    public EmailProcessor(@Qualifier("EmailProvider") NotificationProviderFactory providerFactory) {
        this.providerFactory = providerFactory;
    }

    @Override
    public void process(Notification notification, UUID notificationLogId) {
        providerFactory.getNotificationProvider(notification, notificationLogId);
    }
}
