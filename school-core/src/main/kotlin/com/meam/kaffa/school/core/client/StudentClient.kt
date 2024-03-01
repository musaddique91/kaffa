package com.meam.kaffa.school.core.client

import com.meam.kaffa.common.dto.student.StudentDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "\${student.service.name}")
interface StudentClient {
    @GetMapping("users/{id}")
    fun getStudent(@PathVariable id: Long): StudentDTO
}