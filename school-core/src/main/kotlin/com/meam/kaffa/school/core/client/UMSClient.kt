package com.meam.kaffa.school.core.client

import com.meam.kaffa.common.dto.ums.UserDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "\${ums.service.name}")
interface UMSClient {
    @GetMapping("users/userDetails/{username}")
    fun getUserByUsername(@PathVariable username: String): UserDTO?

    @GetMapping("users/{id}")
    fun getUser(@PathVariable id: Long): UserDTO
}