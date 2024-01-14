package com.meam.kaffa.common.dto.admin.organization

import lombok.Data

@Data
data class OrganizationConfigDTO(
    val id: Long?,
    var language: String?,
    var theme: String?,
    var createUserWithPassword: Boolean?
)
