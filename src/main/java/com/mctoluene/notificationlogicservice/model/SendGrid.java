package com.mctoluene.notificationlogicservice.model;

import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendGrid {
    private String subject;
    private String destination;
    private String content;
    private Map<String, String> additionalParameters;
    private UUID traceId;
    private UUID notificationLogId;
}
