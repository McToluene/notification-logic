package com.mctoluene.notificationlogicservice.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties("spring.r2dbc")
@Configuration
@Data
public class R2DBCConnectionProperties {
    private String username;
    private String password;
    private String url;
    private PoolConfigurationProperties pool;
}
