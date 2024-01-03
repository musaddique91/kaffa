package com.meam.kaffa.gateway.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class UserDetailProvider(private var userClient: WebClient) {
    fun loadUserByUsername(username: String): Mono<UserDetails> {
        return userClient.get().uri("/users/{username}", username)
            .exchangeToMono { it.bodyToMono(UserDetails::class.java) }
    }
}