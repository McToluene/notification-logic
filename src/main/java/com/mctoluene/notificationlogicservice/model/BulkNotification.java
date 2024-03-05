package com.mctoluene.notificationlogicservice.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record BulkNotification(
        UUID id,
        String channelName,
        String providerName,
        List<String> destination,
        String content,
        String title,
        String subject,
        Map<String, Object> additionalParameters,
        UUID traceId) {

    public String getConcatenatedDestinations() {
        return String.join(", ", destination);
    }
}