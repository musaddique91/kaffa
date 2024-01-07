package com.meam.kaffa.common.dto.admin.organization

import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
data class AddressDTO(
    var streetAddress: String?,
    var city: String?,
    var state: String?,
    var postalCode: String?,
    var country: String,
)
