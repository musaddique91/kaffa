package com.meam.kaffa.ums.repository

import com.meam.kaffa.ums.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByOrganizationId(organizationId: Long): List<Role>
}