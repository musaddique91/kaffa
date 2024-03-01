package com.meam.kaffa.common.dto.ums

import lombok.Builder
import lombok.Data

@Data
@Builder
data class PreferencesDTO(
    var id: Long?,
    var language: String?,
    var theme: String?,
    var layout: String?,

    ) {
    constructor() : this(null, null, null, null)
}
