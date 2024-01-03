package com.meam.kaffa.gateway.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val authenticationManager: AuthenticationManager,
    private val securityContextRepository: SecurityContextRepository,
) {

    @Value("\${jwt.public-apis}")
    private lateinit var publicApis: List<String>

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder();

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.cors { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeExchange {
                it.pathMatchers(*publicApis.toTypedArray()).permitAll()
                it.anyExchange().authenticated()
            }
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .requestCache { it.requestCache(NoOpServerRequestCache.getInstance()) }
            .build();
    }
}