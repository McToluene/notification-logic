package com.mctoluene.notificationlogicservice.config.database;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoolConfigurationProperties {
    private boolean enabled;
    private Integer maxSize;
}
