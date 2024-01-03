package com.meam.kaffa.gateway.util

import com.meam.kaffa.gateway.service.UserDetailProvider
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    private lateinit var secretKey: SecretKey;

    @PostConstruct
    fun init(){
        secretKey = Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun <T> getClaimFromToken(token: String, claimsResolver: (Claims) -> T): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver(claims)
    }

    private fun getExpirationDateFromToken(token: String): Date = getClaimFromToken(token, Claims::getExpiration)

    private fun isTokenExpired(token: String): Boolean = getExpirationDateFromToken(token).let { it.before(Date()) }

    fun isInvalid(token: String): Boolean = isTokenExpired(token)

    fun extractUsername(token: String): String = getClaimFromToken(token, Claims::getSubject)
}