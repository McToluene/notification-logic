package com.mctoluene.notificationlogicservice.provider.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.helpers.SMSHelper;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.provider.ProviderSelector;

import reactor.core.publisher.Sinks;

@Service
@Qualifier("sms-africa-talking")
public class AfricaTalkingProvider implements ProviderSelector {
    private final Sinks.Many<Message<String>> many2;

    public AfricaTalkingProvider(Sinks.Many<Message<String>> many2) {
        this.many2 = many2;
    }

    @Override
    public void select(Notification notification, UUID notificationLogId) {
        String message = SMSHelper.buildSMSPayload(notification, notificationLogId);
        many2.emitNext(MessageBuilder.withPayload(message).build(), Sinks.EmitFailureHandler.FAIL_FAST);

    }
}
