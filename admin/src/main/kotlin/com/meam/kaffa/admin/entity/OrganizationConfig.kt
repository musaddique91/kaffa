package com.meam.kaffa.admin.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.Data

@Data
@Entity
@Table(name = "organization_config")
data class OrganizationConfig(
    var language: String?,
    var theme: String?,
    var createUserWithPassword: Boolean?
) : BaseIdEntity()
