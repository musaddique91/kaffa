package com.meam.kaffa.school.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.Data

@Data
@Entity
data class Department(
    @Id var code: String,
    var name: String,
    var organizationId: Long
)
