package com.meam.kaffa.ums.config

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "ums.user.config")
data class ConfigProperties (
    var defaultPassword: String
)