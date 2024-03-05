package com.mctoluene.notificationlogicservice.helpers;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.model.SendGrid;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailHelper {
    private EmailHelper() {
    }

    public static String buildSendGridPayload(Notification notification, UUID notificationLogId) {
        String message = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            SendGrid sendGrid = mapper.convertValue(notification, SendGrid.class);
            sendGrid.setNotificationLogId(notificationLogId);
            message = mapper.writeValueAsString(sendGrid);
        } catch (JsonProcessingException exception) {
            log.error("Failed to convert notification to json for email", exception);
        }
        return message;
    }
}
