package com.meam.kaffa.teacher.config

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "teacher.user.config")
data class ConfigProperties (
    var defaultPassword: String
)