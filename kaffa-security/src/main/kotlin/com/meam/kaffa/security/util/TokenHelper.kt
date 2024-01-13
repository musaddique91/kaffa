package com.meam.kaffa.security.util

import com.meam.kaffa.security.jwt.TokenService
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class TokenHelper(private val tokenService: TokenService) {
    fun organizationId() = tokenService.getOrganizationId(SecurityHelper.getToken()!!)
}