package com.meam.kaffa.notification.service

import com.meam.kaffa.security.model.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsProvider(
    private val umsClient: com.meam.kaffa.notification.client.UMSClient
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return username?.let { umsClient.getUserByUsername(it) } ?: throw UsernameNotFoundException("user not found")
    }
}