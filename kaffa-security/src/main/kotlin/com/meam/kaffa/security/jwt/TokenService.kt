package com.meam.kaffa.security.jwt

import com.meam.kaffa.security.config.JwtProperties
import com.meam.kaffa.security.constants.SecurityConstants
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(jwtProperties: JwtProperties) {
    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.secret.toByteArray()
    )

    fun generate(
        userName: String,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String =
        Jwts.builder()
            .claims()
            .subject(userName)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    fun isValid(token: String, username: String): Boolean {
        val tokenUsername = extractUsername(token)
        return username == tokenUsername && !isExpired(token)
    }

    private fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()
        return parser
            .parseSignedClaims(token)
            .payload
    }

    fun extractUsername(token: String): String = getAllClaims(token).subject

    fun getOrganizationId(token: String) =
        getAllClaims(token).get(SecurityConstants.CLAIM_ORGANIZATIONID) as Long
}