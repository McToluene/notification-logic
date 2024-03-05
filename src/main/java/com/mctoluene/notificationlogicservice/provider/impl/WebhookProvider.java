package com.mctoluene.notificationlogicservice.provider.impl;

import reactor.core.publisher.Sinks;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.helpers.SMSHelper;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.provider.ProviderSelector;

@Service
@Qualifier("webhook")
public class WebhookProvider implements ProviderSelector {
    private final Sinks.Many<Message<String>> many4;

    public WebhookProvider(Sinks.Many<Message<String>> many4) {
        this.many4 = many4;
    }

    @Override
    public void select(Notification notification, UUID notificationLogId) {
        String message = SMSHelper.buildWebHookSMSPayload(notification, notificationLogId);
        many4.emitNext(MessageBuilder.withPayload(message).build(), Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
