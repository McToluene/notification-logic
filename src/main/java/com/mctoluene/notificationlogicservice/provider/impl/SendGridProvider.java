package com.mctoluene.notificationlogicservice.provider.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.helpers.EmailHelper;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.provider.ProviderSelector;

import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Service
@Qualifier("EmailSendGrid")
public class SendGridProvider implements ProviderSelector {
    private final Sinks.Many<Message<String>> many3;

    public SendGridProvider(Many<Message<String>> many3) {
        this.many3 = many3;
    }

    @Override
    public void select(Notification notification, UUID notificationLogId) {
        String message = EmailHelper.buildSendGridPayload(notification, notificationLogId);
        many3.emitNext(MessageBuilder.withPayload(message).build(), Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
