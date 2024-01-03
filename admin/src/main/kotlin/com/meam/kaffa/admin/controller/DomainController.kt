package com.meam.kaffa.admin.controller

import com.meam.kaffa.admin.service.DomainService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("domain")
class DomainController(private val domainService: DomainService) {
    @GetMapping("modules")
    fun getAllOrganizations() = domainService.getModules()
}