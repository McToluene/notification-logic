package com.mctoluene.notificationlogicservice.config.consumer;

import java.util.Objects;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.azure.spring.messaging.checkpoint.Checkpointer;
import com.mctoluene.notificationlogicservice.service.NotificationService;

import static com.azure.spring.messaging.AzureHeaders.CHECKPOINTER;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class NotificationConsumerConfiguration {

    private final NotificationService notificationService;

    public NotificationConsumerConfiguration(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Bean
    public Consumer<Message<String>> consume1() {
        return stringMessage -> {
            Checkpointer checkpointer = (Checkpointer) stringMessage.getHeaders().get(CHECKPOINTER);
            String payload = stringMessage.getPayload();
            log.info("New message received: '{}'", payload);
            notificationService.initiate(payload);
            Objects.requireNonNull(checkpointer).success()
                    .doOnSuccess(s -> log.info("Message '{}' successfully checked", stringMessage.getPayload()))
                    .doOnError(e -> log.error("Error found", e))
                    .block();
        };
    }
}
