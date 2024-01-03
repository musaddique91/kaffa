package com.meam.kaffa.common.dto.ums

import lombok.Builder
import lombok.Data

@Data
@Builder
data class PermissionDTO(
    val code: String,
    val name: String,
)
