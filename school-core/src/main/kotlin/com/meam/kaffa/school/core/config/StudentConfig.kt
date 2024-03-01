package com.meam.kaffa.school.core.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing(auditorAwareRef = "userAuditAware")
class StudentConfig {
}