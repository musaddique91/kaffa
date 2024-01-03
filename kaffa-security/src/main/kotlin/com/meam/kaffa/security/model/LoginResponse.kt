package com.meam.kaffa.security.model

import java.util.*

data class LoginResponse(
    val username: String,
    val token: String,
    val expiryDate: Date,
    val organizationId: Long
//    val organizationIds: List<Long>?,
)
