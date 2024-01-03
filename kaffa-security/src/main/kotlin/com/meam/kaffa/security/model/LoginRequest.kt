package com.meam.kaffa.security.model

data class LoginRequest(val username: String, val password: String, val organizationIds: List<Long>?)
