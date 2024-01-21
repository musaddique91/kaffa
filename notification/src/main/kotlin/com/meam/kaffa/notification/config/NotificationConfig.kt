package com.meam.kaffa.notification.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ConfigProperties::class)
class NotificationConfig {
}