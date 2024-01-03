package com.meam.kaffa.ums.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.Builder
import lombok.Data

@Data
@Entity
data class Permission(
    @Id val code: String,
    val name: String,
)
