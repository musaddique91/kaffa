package com.meam.kaffa.common.dto.ums

import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
data class RoleDTO(
    var id: Long = 0,
    var name: String,
    var permissions: List<PermissionDTO> = emptyList(),
    var organizationId: Long
)

