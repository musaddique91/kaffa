package com.meam.kaffa.common.dto.admin.module

import lombok.Builder
import lombok.Data

@Data
@Builder
class ModuleDTO(
    val code: String,
    val name: String,
    val active: Boolean
)
