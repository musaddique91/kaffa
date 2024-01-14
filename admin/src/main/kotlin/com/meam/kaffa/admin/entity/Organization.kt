package com.meam.kaffa.admin.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import lombok.Data

@Data
@Entity
data class Organization(
    val name: String,
    val description: String?,
    @OneToOne(cascade = [CascadeType.ALL]) var organizationConfig: OrganizationConfig? = null,
    @OneToMany(cascade = [CascadeType.ALL]) var address: List<Address> = emptyList(),
    @OneToMany(cascade = [CascadeType.MERGE]) var modules: MutableList<Module> = mutableListOf(),
): BaseIdEntity()