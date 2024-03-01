package com.meam.kaffa.common.exceptions

class KaffaException(val code: Int, override val message: String?) : RuntimeException()
