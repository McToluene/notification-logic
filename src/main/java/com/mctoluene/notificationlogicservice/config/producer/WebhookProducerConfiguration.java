package com.mctoluene.notificationlogicservice.config.producer;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
@Slf4j
public class WebhookProducerConfiguration {
    @Bean
    public Sinks.Many<Message<String>> many4() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<Message<String>>> supply4(Sinks.Many<Message<String>> many4) {
        return () -> many4.asFlux()
                .doOnNext(m -> log.info("Sending message {} to africa talking", m))
                .doOnError(t -> log.error("Error encountered", t));
    }
}
