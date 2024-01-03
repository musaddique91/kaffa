package com.meam.kaffa.common.exceptions

class AppSysException(val code: Int, override val message: String?) : RuntimeException()
