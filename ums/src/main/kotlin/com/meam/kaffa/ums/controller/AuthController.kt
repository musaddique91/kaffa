package com.meam.kaffa.ums.controller

import com.meam.kaffa.security.model.LoginRequest
import com.meam.kaffa.ums.service.UserAuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("public")
class AuthController(private val userAuthService: UserAuthService) {
    @PostMapping("login")
    fun login(@RequestBody loginRequest: LoginRequest) = userAuthService.login(loginRequest)
}