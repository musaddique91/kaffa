package com.meam.kaffa.school.core.repository

import com.meam.kaffa.school.core.entity.FeeTypes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.transaction.annotation.Transactional

interface FeeTypeRepository : JpaRepository<FeeTypes, String> {
    fun findByOrganizationId(organizationId: Long): List<FeeTypes>

    @Modifying
    @Transactional
    fun deleteByOrganizationId(organizationId: Long): Long

}