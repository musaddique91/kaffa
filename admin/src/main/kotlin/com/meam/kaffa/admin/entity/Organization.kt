package com.meam.kaffa.admin.entity

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
data class Organization(
    @Id @GeneratedValue val id: Long = 0,
    val name: String,
    val description: String,
    @OneToMany(cascade = [CascadeType.ALL]) var address: List<Address> = emptyList(),
    @OneToMany(
        mappedBy = "organization",
        cascade = [CascadeType.ALL]
    ) var modules: MutableList<Module> = mutableListOf(),
)