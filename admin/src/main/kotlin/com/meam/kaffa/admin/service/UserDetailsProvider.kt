package com.meam.kaffa.admin.service

import com.meam.kaffa.admin.client.UMSClient
import com.meam.kaffa.security.model.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsProvider(
    private val umsClient: UMSClient
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return username?.let { umsClient.getUserByUsername(it) } ?: throw UsernameNotFoundException("user not found")
    }
}