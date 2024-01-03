package com.meam.kaffa.admin.controller

import com.meam.kaffa.admin.service.UserService
import com.meam.kaffa.common.dto.ums.UserDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {
    @PostMapping
    fun create(@RequestBody userDTO: UserDTO) = userService.create(userDTO)

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody user: UserDTO): UserDTO = userService.update(id, user)

    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long): UserDTO = userService.getUser(id)

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Long):Boolean {
        return userService.delete(id).run { true }
    }
}