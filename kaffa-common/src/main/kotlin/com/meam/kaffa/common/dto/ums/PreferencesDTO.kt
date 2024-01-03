package com.meam.kaffa.common.dto.ums

import lombok.Builder
import lombok.Data

@Data
@Builder
data class PreferencesDTO(
    val language: String,
    val theme: String,
)
