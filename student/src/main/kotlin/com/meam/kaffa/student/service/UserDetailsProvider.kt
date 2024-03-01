package com.meam.kaffa.student.service

import com.meam.kaffa.security.model.UserDetails
import com.meam.kaffa.student.client.UMSClient
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsProvider(
    private val umsClient: UMSClient
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return username
            ?.let { umsClient.getUserByUsername(it) }
            ?.let { dto ->
                UserDetails(
                    dto.username,
                    dto.enabled ?: true,
                    dto.roles.flatMap { role -> role.permissions }.map { it.code }.toMutableSet(),
                    dto.email,
                    dto.firstName,
                    dto.lastName,
                    dto.phoneNumber,
                    dto.organizationId,
                    dto.gender?.name
                )
            }
            ?: throw UsernameNotFoundException("user not found")
    }
}