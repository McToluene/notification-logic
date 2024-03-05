package com.mctoluene.notificationlogicservice.model.request;

import java.util.UUID;

public record WebHookSMS(UUID notificationLogId, String url, String payload) {

}
