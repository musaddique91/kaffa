package com.meam.kaffa.security.util

import com.meam.kaffa.security.model.UserDetails
import org.springframework.security.core.context.SecurityContextHolder

class SecurityHelper {
    companion object {
        val sessionUser: UserDetails?
            get() {
                val authentication = SecurityContextHolder.getContext().authentication
                return if (authentication != null && authentication.credentials is UserDetails) {
                    authentication.credentials as UserDetails
                } else {
                    null
                }
            }

        val userName: String?
            get() = sessionUser?.username

        val token = SecurityContextHolder.getContext().authentication.principal.toString()
    }
}