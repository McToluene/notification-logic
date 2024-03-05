package com.mctoluene.notificationlogicservice.factory.provider.impl;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.enums.NotificationProvider;
import com.mctoluene.notificationlogicservice.factory.provider.NotificationProviderFactory;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.provider.ProviderSelector;

import lombok.extern.slf4j.Slf4j;

@Service
@Qualifier("EmailProvider")
@Slf4j
public class EmailProviderFactory implements NotificationProviderFactory {
    private final Map<NotificationProvider, ProviderSelector> emailProviders;

    public EmailProviderFactory(@Qualifier("EmailSendGrid") ProviderSelector sendGrid) {
        emailProviders = new EnumMap<>(NotificationProvider.class);
        emailProviders.put(NotificationProvider.SENDGRID, sendGrid);
    }

    @Override
    public void getNotificationProvider(Notification notification, UUID notificationLogId) {
        try {
            if (notification != null && !notification.channelName().isEmpty()) {
                NotificationProvider notificationProvider = NotificationProvider
                        .valueOf(notification.providerName().toUpperCase());
                emailProviders.get(notificationProvider).select(notification, notificationLogId);
            }
        } catch (IllegalArgumentException exception) {
            log.error("Failed to get email providers", exception);
        }
    }
}
