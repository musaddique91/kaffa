package com.meam.kaffa.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties (
    var secret: String,
    var accessTokenExpiration: Long,
    var refreshTokenExpiration: Long,
    var publicApis: List<String> = listOf()
)