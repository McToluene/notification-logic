package com.mctoluene.notificationlogicservice.helpers;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mctoluene.notificationlogicservice.model.Notification;
import com.mctoluene.notificationlogicservice.model.SMS;
import com.mctoluene.notificationlogicservice.model.request.WebHookSMS;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SMSHelper {
    private SMSHelper() {
    }

    public static String buildSMSPayload(Notification notification, UUID notificationLogId) {
        String message = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            SMS sms = mapper.convertValue(notification, SMS.class);
            sms.setNotificationLogId(notificationLogId);
            message = mapper.writeValueAsString(sms);
        } catch (JsonProcessingException exception) {
            log.error("Failed to convert notification to json", exception);
        }
        return message;
    }

    public static String buildWebHookSMSPayload(Notification notification, UUID notificationLogId) {
        String message = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), false);
            WebHookSMS sms = new WebHookSMS(notificationLogId,
                    notification.additionalParameters().get("url").toString(),
                    mapper.writeValueAsString(notification.additionalParameters().get("data")));
            message = mapper.writeValueAsString(sms);
        } catch (JsonProcessingException exception) {
            log.error("Failed to convert notification to json", exception);
        }
        return message;
    }
}
