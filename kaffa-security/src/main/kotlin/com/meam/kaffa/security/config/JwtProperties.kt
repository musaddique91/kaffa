package com.meam.kaffa.security.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties (
    var secret: String,
    var accessTokenExpiration: Long,
    var refreshTokenExpiration: Long,
    var publicApis: List<String> = listOf(),
    var technicalUserId: String,
    var technicalUserPassword: String
)