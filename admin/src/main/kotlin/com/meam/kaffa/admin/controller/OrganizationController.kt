package com.meam.kaffa.admin.controller

import com.meam.kaffa.admin.service.OrganizationService
import com.meam.kaffa.common.dto.admin.organization.OrganizationDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("organizations")
class OrganizationController(
    private val organizationService: OrganizationService
) {

    @GetMapping
    fun getAllOrganizations() = organizationService.getOrganizations()

    @PostMapping
    fun createOrganization(@RequestBody organizationDTO: OrganizationDTO) =
        organizationService.createOrganization(organizationDTO)

    @PutMapping("/{id}")
    fun updateOrganization(@PathVariable id: Long, @RequestBody organizationDTO: OrganizationDTO) =
        organizationService.updateOrganization(id, organizationDTO)

    @DeleteMapping("/{id}")
    fun deleteOrganization(@PathVariable id: Long) = organizationService.deleteOrganization(id)
}