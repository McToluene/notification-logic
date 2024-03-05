package com.mctoluene.notificationlogicservice.model;

import java.util.Map;
import java.util.UUID;

public record Notification(
        UUID id,
        String channelName,
        String providerName,
        String destination,
        String content,
        String title,
        String subject,
        Map<String, Object> additionalParameters,
        UUID traceId) {
}
