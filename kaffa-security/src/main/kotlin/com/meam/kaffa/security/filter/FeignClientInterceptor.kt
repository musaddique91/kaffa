package com.meam.kaffa.security.filter

import com.meam.kaffa.security.jwt.TechnicalUserTokenProvider
import com.meam.kaffa.security.util.SecurityHelper
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class FeignClientInterceptor(private val technicalUserTokenProvider: TechnicalUserTokenProvider) : RequestInterceptor {
    override fun apply(template: RequestTemplate?) {
        if (template?.headers()?.get(HttpHeaders.AUTHORIZATION) == null) {
            val token = SecurityHelper.getToken() ?: "Bearer ${technicalUserTokenProvider.getTechnicalUserToken()}"
            template?.header(HttpHeaders.AUTHORIZATION, token)
        }
    }
}