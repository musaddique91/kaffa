package com.meam.kaffa.security.exceptions

class AppSysException(val code: Int, override val message: String?) : RuntimeException()
