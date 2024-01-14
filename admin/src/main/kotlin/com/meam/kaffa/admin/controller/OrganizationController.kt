package com.meam.kaffa.admin.controller

import com.meam.kaffa.admin.service.OrganizationService
import com.meam.kaffa.common.dto.admin.organization.OrganizationDTO
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("organizations")
class OrganizationController(
    private val organizationService: OrganizationService
) {

    @GetMapping
    fun getAllOrganizations() = organizationService.getOrganizations()

    @GetMapping("{id}")
    fun getOrganizationById(@PathVariable id: Long) = organizationService.getOrganizationById(id)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SUPREME_ADMIN')")
    fun createOrganization(@RequestBody organizationDTO: OrganizationDTO) =
        organizationService.createOrganization(organizationDTO)

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SUPREME_ADMIN')")
    fun updateOrganization(@PathVariable id: Long, @RequestBody organizationDTO: OrganizationDTO) =
        organizationService.updateOrganization(id, organizationDTO)

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SUPREME_ADMIN')")
    fun deleteOrganization(@PathVariable id: Long) = organizationService.deleteOrganization(id)
}