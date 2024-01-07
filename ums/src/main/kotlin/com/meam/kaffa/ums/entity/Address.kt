package com.meam.kaffa.ums.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.Entity
import lombok.Data

@Entity
@Data
data class Address(
    var streetAddress: String,
    var city: String,
    var state: String,
    var postalCode: String,
    var country: String,
): BaseIdEntity()
