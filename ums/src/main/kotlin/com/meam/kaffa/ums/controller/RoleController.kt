package com.meam.kaffa.ums.controller

import com.meam.kaffa.common.dto.ums.RoleDTO
import com.meam.kaffa.ums.service.RoleService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("roles")
class RoleController(private val roleService: RoleService) {
    @PostMapping
    fun createRole(@RequestBody roleDTO: RoleDTO) = roleService.createRole(roleDTO)
}