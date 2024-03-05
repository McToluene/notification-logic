package com.mctoluene.notificationlogicservice.processor.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.factory.provider.NotificationProviderFactory;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.processor.NotificationProcessor;

@Service
@Qualifier("SMSProcessor")
public class SMSProcessor implements NotificationProcessor {
    private final NotificationProviderFactory providerFactory;

    public SMSProcessor(@Qualifier("SMSProvider") NotificationProviderFactory providerFactory) {
        this.providerFactory = providerFactory;
    }

    @Override
    public void process(Notification notification, UUID notificationLogId) {
        providerFactory.getNotificationProvider(notification, notificationLogId);
    }

}
