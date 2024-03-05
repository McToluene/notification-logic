package com.mctoluene.notificationlogicservice.config.database;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
@RequiredArgsConstructor
@Slf4j
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {

    private final R2DBCConnectionProperties r2DBCConnectionProperties;

    @NotNull
    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        log.info("PG URL: {}", r2DBCConnectionProperties.getUrl());
        log.info("PG PASSWORD: {}", r2DBCConnectionProperties.getPassword());
        ConnectionFactoryOptions baseOptions = ConnectionFactoryOptions.parse(r2DBCConnectionProperties.getUrl());
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .from(baseOptions)
                .option(PROTOCOL, "postgresql")
                .option(USER, r2DBCConnectionProperties.getUsername())
                .option(PASSWORD, r2DBCConnectionProperties.getPassword())
                .option(MAX_SIZE, r2DBCConnectionProperties.getPool().getMaxSize())
                .build());
    }
}
