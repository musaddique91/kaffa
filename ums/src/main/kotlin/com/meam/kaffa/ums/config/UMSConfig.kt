package com.meam.kaffa.ums.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing(auditorAwareRef = "userAuditAware")
@EnableConfigurationProperties(ConfigProperties::class)
class UMSConfig {
}