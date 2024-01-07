package com.meam.kaffa.ums.controller

import com.meam.kaffa.common.dto.ums.UserDTO
import com.meam.kaffa.ums.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("userDetails/{username}")
    fun getUserDetailsByUsername(@PathVariable username: String) = userService.getUserByUsername(username);

    @PostMapping
    fun create(@RequestBody user: UserDTO): UserDTO = userService.create(user)

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody user: UserDTO): UserDTO = userService.update(id, user)

    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long): UserDTO = userService.getUser(id)

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Long): Boolean {
        return userService.delete(id).run { true }
    }
}