package com.mctoluene.notificationlogicservice.config.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
@Slf4j
public class AfricaTalkingProducerConfiguration {
    @Bean
    public Sinks.Many<Message<String>> many2() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<Message<String>>> supply2(Sinks.Many<Message<String>> many2) {
        return () -> many2.asFlux()
                .doOnNext(m -> log.info("Sending message {} to africa talking", m))
                .doOnError(t -> log.error("Error encountered", t));
    }
}
