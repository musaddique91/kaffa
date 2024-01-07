package com.meam.kaffa.common.dto

import java.time.LocalDateTime

open class AuditDTO(
    var createdAt: LocalDateTime? = null,
    var modifiedAt: LocalDateTime? = null,
    var createdBy: String? = null,
    var modifiedBy: String? = null,
    var version: Int? = null
)