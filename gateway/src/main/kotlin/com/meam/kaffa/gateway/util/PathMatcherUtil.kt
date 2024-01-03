package com.meam.kaffa.gateway.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher

@Component
class PathMatcherUtil(private val antPathMatcher: AntPathMatcher = AntPathMatcher()) {
    @Value("\${jwt.public-apis}")
    private lateinit var publicApis: List<String>


    fun isPublicPath(path: String): Boolean {
        return publicApis.any { antPathMatcher.match(it, path) }
    }
}