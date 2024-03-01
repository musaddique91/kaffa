package com.meam.kaffa.school.core.repository

import com.meam.kaffa.school.core.entity.Department
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository : JpaRepository<Department, String> {
    fun findByOrganizationId(organizationId: Long): List<Department>
}