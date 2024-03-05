package com.mctoluene.notificationlogicservice.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("notification_integration_logs")
@EqualsAndHashCode(callSuper = false)
public class NotificationIntegrationLog implements Persistable<UUID> {

    @Id
    private UUID id;
    private String channelName;
    private String providerName;
    private String requestBody;
    private String responseBody;
    private String status;
    private String createdBy;
    private String callbackResponse;
    private String referenceId;
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Override
    public boolean isNew() {
        boolean result = Objects.isNull(id);
        this.id = result ? UUID.randomUUID() : this.id;
        return result;
    }
}