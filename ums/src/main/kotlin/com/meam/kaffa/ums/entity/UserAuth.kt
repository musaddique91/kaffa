package com.meam.kaffa.ums.entity

import com.meam.kaffa.common.entity.BaseEntity
import com.meam.kaffa.common.enumrate.AdminUserType
import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "user_auth")
data class UserAuth(
    @Column(nullable = false, length = 100, unique = true) val username: String,
    @Column(nullable = false, length = 1000) val password: String,
    @Column(nullable = false) val enable: Boolean = true,
    @Column(nullable = true) var isTempPassword: Boolean = false,
    @Enumerated(EnumType.STRING)
    @Column(nullable = true) var adminUserType: AdminUserType,
) : BaseEntity()