package com.meam.kaffa.ums.service

import com.meam.kaffa.security.model.UserDetails
import com.meam.kaffa.ums.repository.UserAuthRepository
import com.meam.kaffa.ums.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsProvider(
    private val userAuthRepository: UserAuthRepository,
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return username
            ?.let { userAuthRepository.findByUsername(it) }
            ?.let { userRepository.findByUserAuthId(it.id!!) }
            ?.let {
                UserDetails(
                    it.userAuth.username,
                    it.userAuth.enabled,
                    it.roles.flatMap { role -> role.permissions }.map { it.code }.toMutableSet(),
                    it.email,
                    it.firstName,
                    it.lastName,
                    it.phoneNumber,
                    it.organizationId
                )
            } ?: throw UsernameNotFoundException("User not found")
    }
}