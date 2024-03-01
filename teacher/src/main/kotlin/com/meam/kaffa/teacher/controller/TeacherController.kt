package com.meam.kaffa.teacher.controller

import com.meam.kaffa.common.dto.teacher.TeacherDTO
import com.meam.kaffa.teacher.service.TeacherService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class TeacherController(
    private val teacherService: TeacherService,
) {

    @PostMapping
    fun create(@RequestBody user: TeacherDTO): TeacherDTO = teacherService.create(user)

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody user: TeacherDTO): TeacherDTO = teacherService.update(id, user)

    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long): TeacherDTO = teacherService.getUser(id)

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Long): Boolean {
        return teacherService.delete(id).run { true }
    }
}