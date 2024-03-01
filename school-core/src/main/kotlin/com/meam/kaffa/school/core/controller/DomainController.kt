package com.meam.kaffa.school.core.controller

import com.meam.kaffa.common.dto.school.DepartmentDTO
import com.meam.kaffa.common.dto.school.FeeTypesDTO
import com.meam.kaffa.school.core.service.DomainService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("domain")
class DomainController(
    private val domainService: DomainService
) {

    @GetMapping("departments")
    fun getDepartments(): List<DepartmentDTO> {
        return domainService.getDepartments()
    }

    @GetMapping("feeTypes")
    fun getFeeTypes(): List<FeeTypesDTO> {
        return domainService.getFeeTypes()
    }

    @PostMapping("feeTypes")
    fun addFeeTypes(@RequestBody feeTypes: List<FeeTypesDTO>): List<FeeTypesDTO> {
        return domainService.setFeeTypes(feeTypes)
    }

}