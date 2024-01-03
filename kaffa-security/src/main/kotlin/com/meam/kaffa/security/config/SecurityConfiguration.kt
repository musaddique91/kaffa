package com.meam.kaffa.security.config

import com.meam.kaffa.security.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtProperties: JwtProperties
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder();

    @Bean
    fun authenticationProvider(): AuthenticationProvider =
        DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailsService)
                it.setPasswordEncoder(passwordEncoder())
            }


    @Bean
    @Order(0)
    fun publicSecurityChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .cors { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .securityMatcher(*jwtProperties.publicApis.toTypedArray())
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
            }
            .build()
    }

    @Bean
    @Order(1)
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter
    ): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .cors { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .securityMatcher("/users/**")
            .authorizeHttpRequests {
                it
                    .anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }


}