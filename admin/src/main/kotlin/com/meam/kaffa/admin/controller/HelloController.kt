package com.meam.kaffa.admin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("public")
class HelloController {
    @GetMapping("hello")
    fun hello(): String = "hello"
}