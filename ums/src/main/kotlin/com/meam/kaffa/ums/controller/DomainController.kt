package com.meam.kaffa.ums.controller

import com.meam.kaffa.ums.service.DomainService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("domain")
class DomainController(private val domainService: DomainService) {
    @GetMapping("permissions")
    fun getPermissions() = domainService.getPermissions()
}