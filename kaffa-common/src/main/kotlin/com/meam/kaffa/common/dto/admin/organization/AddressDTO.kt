package com.meam.kaffa.common.dto.admin.organization

import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
class AddressDTO(
    val streetAddress: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String,
)
