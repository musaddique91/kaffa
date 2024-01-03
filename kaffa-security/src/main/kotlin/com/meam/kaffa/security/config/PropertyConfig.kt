package com.meam.kaffa.security.config

import com.meam.kaffa.security.config.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources

@Configuration
@PropertySources(
    PropertySource(value = ["classpath:security-config.properties"])
)
@EnableConfigurationProperties(JwtProperties::class)
class PropertyConfig {
}