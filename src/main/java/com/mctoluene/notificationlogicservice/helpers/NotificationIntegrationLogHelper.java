package com.mctoluene.notificationlogicservice.helpers;

import java.time.LocalDateTime;

import com.mctoluene.notificationlogicservice.domain.NotificationIntegrationLog;

public class NotificationIntegrationLogHelper {
    private NotificationIntegrationLogHelper() {
    }

    public static NotificationIntegrationLog buildIntegrationLog(String requestBody, String channelName,
            String providerName) {
        return NotificationIntegrationLog.builder()
                .channelName(channelName)
                .providerName(providerName)
                .createdBy("NOTIFICATION-LOGIC")
                .requestBody(requestBody)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }
}
