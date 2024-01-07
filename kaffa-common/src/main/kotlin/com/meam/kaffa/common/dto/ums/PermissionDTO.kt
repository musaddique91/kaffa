package com.meam.kaffa.common.dto.ums

import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
data class PermissionDTO(
    val code: String,
    val name: String,
)
