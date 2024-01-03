package com.meam.kaffa.gateway.config

import com.meam.kaffa.gateway.service.UserDetailProvider
import com.meam.kaffa.gateway.util.JwtUtil
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
class AuthenticationManager(
    private val jwtUtil: JwtUtil,
    private val userDetailProvider: UserDetailProvider
) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        authentication?.credentials.toString().let { token ->
            if (jwtUtil.isInvalid(token)) {
                return Mono.empty();
            }
            val claims = jwtUtil.getAllClaimsFromToken(token)
            val data = UsernamePasswordAuthenticationToken(claims.subject, token, emptyList())
            return Mono.just(data);
        }
    }
}