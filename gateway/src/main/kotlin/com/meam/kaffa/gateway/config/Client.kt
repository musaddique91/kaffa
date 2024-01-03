package com.meam.kaffa.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Client {
    @Bean
    fun userWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("lb://ums")
            .defaultHeader("REQUESTER", "GATEWAY")
            .build();
    }
}