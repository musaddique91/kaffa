package com.meam.kaffa.student.controller

import com.meam.kaffa.common.dto.student.StudentDTO
import com.meam.kaffa.student.service.StudentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class StudentController(
    private val studentService: StudentService,
) {

    @PostMapping
    fun create(@RequestBody user: StudentDTO): StudentDTO = studentService.create(user)

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody user: StudentDTO): StudentDTO = studentService.update(id, user)

    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long): StudentDTO = studentService.getUser(id)

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Long): Boolean {
        return studentService.delete(id).run { true }
    }
}