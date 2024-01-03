package com.meam.kaffa.common.dto.ums

import lombok.Builder
import lombok.Data

@Data
@Builder
data class RoleDTO(
    val id: Long = 0,
    val name: String,
    val permissions: List<PermissionDTO> = emptyList(),
    val organizationId: Long
)

