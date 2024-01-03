package com.meam.kaffa.admin.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.Entity
import lombok.Data

@Data
@Entity
data class Address(
    val streetAddress: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String,
) : BaseIdEntity()
