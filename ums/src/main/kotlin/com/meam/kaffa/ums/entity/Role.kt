package com.meam.kaffa.ums.entity

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "role_permission",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_code")]
    )
    var permissions: List<Permission> = emptyList(),
    var organizationId: Long,
    var internalRole: Boolean?
)

