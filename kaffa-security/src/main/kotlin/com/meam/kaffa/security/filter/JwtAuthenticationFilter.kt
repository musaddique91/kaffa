package com.meam.kaffa.security.filter

import com.meam.kaffa.security.constants.SecurityConstants
import com.meam.kaffa.security.jwt.TokenService
import com.meam.kaffa.security.model.UserDetails
import com.meam.kaffa.security.util.PathMatcherUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private var userDetailsService: UserDetailsService,
    private var tokenService: TokenService,
    private var pathMatcherUtil: PathMatcherUtil
) : OncePerRequestFilter() {
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return pathMatcherUtil.isPublicPath(request.servletPath)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.getHeader(HttpHeaders.AUTHORIZATION)?.let { authHeader ->
            if (authHeader.validBearerTokenHeader()) {
                val jwtToken = authHeader.extractToken();
                val username = tokenService.extractUsername(jwtToken)
                if (username.isNotEmpty() && SecurityContextHolder.getContext().authentication == null) {
                    val foundUser =userDetailsService.loadUserByUsername(username) as UserDetails
                    if (tokenService.isValid(jwtToken, username))
                        updateSecurityContext(authHeader, foundUser, request)
                    filterChain.doFilter(request, response)
                }
            }else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        }
    }


    private fun String?.validBearerTokenHeader() =
        this != null && this.startsWith(SecurityConstants.BEARER_PREFIX)

    private fun String.extractToken() = this.substringAfter(SecurityConstants.BEARER_PREFIX)

    private fun updateSecurityContext(token: String, foundUser: UserDetails, request: HttpServletRequest) {
        val authorities = foundUser.dbAuthorities.filter { !it.startsWith("ROLE_") }.map { "ROLE_$it" }.toMutableList()
        val authToken = UsernamePasswordAuthenticationToken(token, foundUser, AuthorityUtils.createAuthorityList(authorities))
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
    }
}