package com.meam.kaffa.common.config

import org.springframework.data.domain.AuditorAware
import java.util.*

class KaffaAuditorAware: AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        TODO("Not yet implemented")
    }
}