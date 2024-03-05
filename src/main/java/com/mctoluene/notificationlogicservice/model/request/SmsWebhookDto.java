package com.mctoluene.notificationlogicservice.model.request;

public record SmsWebhookDto(String id, String status, String statusMessage, Object originalPayload) {
}
