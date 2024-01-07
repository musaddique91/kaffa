package com.meam.kaffa.security.jwt

import com.meam.kaffa.security.config.JwtProperties
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class TechnicalUserTokenProvider(
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
) {
    private var technicalUserToken = "";
    fun getTechnicalUserToken(): String {
        if (technicalUserToken.isNotEmpty() &&
            tokenService.isValid(technicalUserToken, jwtProperties.technicalUserId)
        ) {
            return technicalUserToken;
        } else {
            val expirationTime = Date.from(
                LocalDateTime.now().plusHours(jwtProperties.accessTokenExpiration).atZone(
                    ZoneId.systemDefault()
                ).toInstant()
            )
            technicalUserToken = tokenService.generate(
                jwtProperties.technicalUserId,
                expirationTime,
                mutableMapOf()
            )
            return technicalUserToken;
        }
    }
}