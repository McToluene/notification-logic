package com.mctoluene.notificationlogicservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import com.mctoluene.notificationlogicservice.enums.ChannelParameterType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("notification_channels")
@EqualsAndHashCode(callSuper = false)
public class NotificationChannel implements Persistable<UUID> {

    @Id
    private UUID id;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
    private String channelName;
    private String channelDescription;
    private ChannelParameterType channelParameterType;

    private String status;

    @Override
    public boolean isNew() {
        boolean result = Objects.isNull(id);
        this.id = result ? UUID.randomUUID() : this.id;
        return result;
    }
}
