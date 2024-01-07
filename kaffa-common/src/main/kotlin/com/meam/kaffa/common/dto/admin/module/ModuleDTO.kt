package com.meam.kaffa.common.dto.admin.module

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Builder
import lombok.Data

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ModuleDTO(
    val code: String,
    val name: String?,
    val active: Boolean?
)
