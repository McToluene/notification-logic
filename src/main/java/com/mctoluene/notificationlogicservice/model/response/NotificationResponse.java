package com.mctoluene.notificationlogicservice.model.response;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponse {
    private UUID notificationLogId;
    private String referenceId;
    private String responseObject;
    private String status;
}
