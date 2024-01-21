package com.meam.kaffa.ums.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@ConfigurationProperties(prefix = "user.config")
data class ConfigProperties (
    var defaultPassword: String
)