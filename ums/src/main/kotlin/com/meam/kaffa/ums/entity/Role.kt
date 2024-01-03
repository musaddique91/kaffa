package com.meam.kaffa.ums.entity

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "role_permission",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_code")]
    )
    val permissions: List<Permission> = emptyList(),
    val organizationId: Long,
    val internalRole: Boolean?
)

