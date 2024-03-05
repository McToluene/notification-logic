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
@Qualifier("WebHookProvider")
@Slf4j
public class WebProviderHookFactory implements NotificationProviderFactory {
    private final Map<NotificationProvider, ProviderSelector> smsProviders;

    public WebProviderHookFactory(
            @Qualifier("webhook") ProviderSelector webHook) {
        smsProviders = new EnumMap<>(NotificationProvider.class);
        smsProviders.put(NotificationProvider.WEBHOOK, webHook);
    }

    @Override
    public void getNotificationProvider(Notification notification, UUID notificationLogId) {
        try {
            if (notification != null && !notification.channelName().isEmpty()) {
                NotificationProvider notificationProvider = NotificationProvider
                        .valueOf(notification.providerName().toUpperCase());
                smsProviders.get(notificationProvider).select(notification, notificationLogId);
            }
        } catch (IllegalArgumentException exception) {
            log.error("Failed to get sms providers", exception);
        }
    }
}
