package com.meam.kaffa.ums.service

import com.meam.kaffa.security.config.JwtProperties
import com.meam.kaffa.security.constants.SecurityConstants
import com.meam.kaffa.security.jwt.TokenService
import com.meam.kaffa.security.model.LoginRequest
import com.meam.kaffa.security.model.LoginResponse
import com.meam.kaffa.ums.repository.UserAuthRepository
import com.meam.kaffa.ums.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.security.auth.login.CredentialException

@Service
@Slf4j
class UserAuthService(
    private val userAuthRepository: UserAuthRepository,
    private val userRepository: UserRepository,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
) {
    fun login(loginRequest: LoginRequest): LoginResponse {
        return userAuthRepository.findByUsername(loginRequest.username)
            ?.let {
                val matched = BCryptPasswordEncoder().matches(loginRequest.password, it.password)
                if (!matched) throw CredentialException("incorrect username/password")
                it
            }?.let {
                userRepository.findByUserAuthId(it.id!!)
            }?.let {
                val expirationTime = Date.from(
                    LocalDateTime.now().plusHours(jwtProperties.accessTokenExpiration).atZone(
                        ZoneId.systemDefault()
                    ).toInstant()
                )

                val additionalClaims = mutableMapOf<String, Any>(
                    SecurityConstants.CLAIM_EMAIL to (it.email ?: "default"),
                    SecurityConstants.CLAIM_FIRSTNAME to it.firstName,
                    SecurityConstants.CLAIM_LASTNAME to it.lastName,
                    SecurityConstants.CLAIM_ORGANIZATIONID to it.organizationId
                )
//                loginRequest.organizationIds?.let { reqOrgIds ->
//                    it.organizationIds.filter { id -> reqOrgIds.contains(id) }
//                        .let { orgId -> additionalClaims[SecurityConstants.CLAIM_ORGANIZATIONIDS] = orgId }
//                }
                val token = tokenService.generate(
                    it.userAuth.username,
                    expirationTime,
                    additionalClaims
                )
                LoginResponse(
                    username = it.userAuth.username,
                    token = token,
                    expiryDate = expirationTime,
                    organizationId = it.organizationId
                )
            } ?: throw UsernameNotFoundException("User not found")
    }
}