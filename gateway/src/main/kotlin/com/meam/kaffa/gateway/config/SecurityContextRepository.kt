package com.meam.kaffa.gateway.config

import com.meam.kaffa.gateway.util.JwtUtil
import com.meam.kaffa.gateway.util.PathMatcherUtil
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class SecurityContextRepository(
    private val jwtUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager,
    private val pathMatcherUtil: PathMatcherUtil
) : ServerSecurityContextRepository {
    @Throws(UnsupportedOperationException::class)
    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
        throw UnsupportedOperationException("Not supported yet.");
    }

    override fun load(swe: ServerWebExchange): Mono<SecurityContext> {
        if (pathMatcherUtil.isPublicPath(swe.request.uri.path)) {
            return Mono.empty()
        }
        val authHeader = swe.request.headers.getFirst(HttpHeaders.AUTHORIZATION);
        return if (!authHeader.isNullOrEmpty()) {
            val authToken = authHeader.substringAfter("Bearer ")
            if (jwtUtil.isInvalid(authToken)) {
                return Mono.empty()
            }
            val username = jwtUtil.extractUsername(authToken)
            val auth = UsernamePasswordAuthenticationToken(username, authToken, emptyList())
            authenticationManager.authenticate(auth).mapNotNull { authentication ->
                val context = SecurityContextImpl(authentication)
                SecurityContextHolder.setContext(context)
                ReactiveSecurityContextHolder.withAuthentication(authentication)
                context
            }
        } else {
            return Mono.error(AuthenticationCredentialsNotFoundException("User not authenticated"));
        }
    }
}