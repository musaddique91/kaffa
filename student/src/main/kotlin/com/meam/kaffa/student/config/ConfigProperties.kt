package com.meam.kaffa.student.config

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "student.user.config")
data class ConfigProperties (
    var defaultPassword: String
)