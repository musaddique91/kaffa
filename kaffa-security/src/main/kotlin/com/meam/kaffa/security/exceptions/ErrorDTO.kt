package com.meam.kaffa.security.exceptions

data class ErrorDTO(val code: Int, val message: String?, val path: String, val httpStatus: Int)
