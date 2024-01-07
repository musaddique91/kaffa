package com.meam.kaffa.admin.client

import com.meam.kaffa.common.dto.ums.PermissionDTO
import com.meam.kaffa.common.dto.ums.UserDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(name = "\${ums.service.name}")
interface UMSClient {
    @GetMapping("users/userDetails/{username}")
    fun getUserByUsername(@PathVariable username: String): UserDTO?

    @PostMapping("users")
    fun createUser(@RequestBody userDTO: UserDTO): UserDTO

    @PutMapping("users/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: UserDTO): UserDTO

    @GetMapping("users/{id}")
    fun getUser(@PathVariable id: Long): UserDTO

    @DeleteMapping("users{id}")
    fun deleteUser(@PathVariable id: Long)

    @GetMapping("domain/permissions")
    fun getPermissions(): List<PermissionDTO>
}