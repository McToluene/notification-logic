package com.mctoluene.notificationlogicservice.config;

import brave.baggage.BaggageField;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {
    @Bean
    @Qualifier("traceId")
    public BaggageField trackingCodeTraceField() {
        return BaggageField.create("traceId");
    }
}
