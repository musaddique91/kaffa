package com.meam.kaffa.student.client

import com.meam.kaffa.common.dto.admin.organization.OrganizationDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "\${admin.service.name}")
interface AdminClient {
    @GetMapping("organizations/{id}")
    fun getOrganizationById(@PathVariable id: Long): OrganizationDTO
}