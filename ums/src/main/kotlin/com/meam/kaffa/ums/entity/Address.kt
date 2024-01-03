package com.meam.kaffa.ums.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.Entity
import lombok.Builder
import lombok.Data

@Entity
@Data
data class Address(
    val streetAddress: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String,
): BaseIdEntity()
