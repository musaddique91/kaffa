package com.meam.kaffa.common.model

import lombok.Builder
import lombok.Data

@Data
@Builder
data class KeyValue(
    val key: String,
    val value: String
)