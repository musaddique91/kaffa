package com.meam.kaffa.common.model

import lombok.Builder
import lombok.Data

@Data
@Builder
data class KeyValue(
    var key: String,
    var value: String
)