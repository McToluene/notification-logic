package com.mctoluene.notificationlogicservice.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SMS {
    private String content;
    private String destination;
    private UUID traceId;
    private Integer retryCount;
    private UUID notificationLogId;
}
