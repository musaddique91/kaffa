package com.meam.kaffa.school.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Data

@Data
@Entity
@Table(name = "fee_types")
data class FeeTypes(
    @Id var code: String,
    var name: String,
    var organizationId: Long
)
