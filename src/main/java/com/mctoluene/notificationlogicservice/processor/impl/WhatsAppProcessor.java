package com.mctoluene.notificationlogicservice.processor.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.processor.NotificationProcessor;

@Service
@Qualifier("WhatsAppProcessor")
public class WhatsAppProcessor implements NotificationProcessor {

    @Override
    public void process(Notification notification, UUID notificationLogId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }

}
