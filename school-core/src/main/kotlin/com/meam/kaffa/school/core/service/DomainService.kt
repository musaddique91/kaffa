package com.meam.kaffa.school.core.service

import com.meam.kaffa.common.dto.school.DepartmentDTO
import com.meam.kaffa.common.dto.school.FeeTypesDTO
import com.meam.kaffa.school.core.entity.FeeTypes
import com.meam.kaffa.school.core.repository.DepartmentRepository
import com.meam.kaffa.school.core.repository.DivisionRepository
import com.meam.kaffa.school.core.repository.FeeTypeRepository
import com.meam.kaffa.school.core.repository.GradeRepository
import com.meam.kaffa.security.util.TokenHelper
import org.springframework.stereotype.Service

@Service
class DomainService(
    private val feeTypeRepository: FeeTypeRepository,
    private val departmentRepository: DepartmentRepository,
    private val gradeRepository: GradeRepository,
    private val divisionRepository: DivisionRepository,
    private val tokenHelper: TokenHelper
) {

    fun getFeeTypes(): List<FeeTypesDTO> {
        var feeTypes = feeTypeRepository.findByOrganizationId(tokenHelper.organizationId())
        if (feeTypes.isEmpty()) {
            feeTypes = feeTypeRepository.findAll().toMutableList();
        }
        return feeTypes.map { FeeTypesDTO(it.code, it.name) }
    }

    fun getDepartments(): List<DepartmentDTO> {
        var departments = departmentRepository.findByOrganizationId(tokenHelper.organizationId())
        if (departments.isEmpty()) {
            departments = departmentRepository.findAll().toMutableList()
        }
        return departments.map { DepartmentDTO(it.code, it.name) }
    }

    fun setFeeTypes(feeTypes: List<FeeTypesDTO>): List<FeeTypesDTO> {
        val organizationId = tokenHelper.organizationId()
        feeTypeRepository.deleteByOrganizationId(organizationId)
        return feeTypeRepository.saveAll(
            feeTypes.map { FeeTypes(it.code ?: it.name, it.name, organizationId) }
        ).map { FeeTypesDTO(it.code, it.name) }
    }
}