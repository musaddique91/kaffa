package com.meam.kaffa.admin.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import lombok.Data

@Data
@Entity
data class Module(
    @Id
    val code: String,
    val name: String,
    val active: Boolean,
    @ManyToOne @JoinColumn(name = "organization_id") val organization: Organization
)