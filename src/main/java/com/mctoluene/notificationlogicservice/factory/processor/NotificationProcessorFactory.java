package com.mctoluene.notificationlogicservice.factory.processor;

import java.util.UUID;

import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.model.request.SmsWebhookDto;

public interface NotificationProcessorFactory {
    void process(Notification notification, UUID notificationLogId);

    void processCallback(SmsWebhookDto smsWebhookEvent);
}
