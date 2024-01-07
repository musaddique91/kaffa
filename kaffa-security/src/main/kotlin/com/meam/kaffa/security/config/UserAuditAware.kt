package com.meam.kaffa.security.config

import com.meam.kaffa.security.util.SecurityHelper
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserAuditAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return SecurityHelper.userName?.let { Optional.of(it) } ?: Optional.of("SYSTEM")
    }
}