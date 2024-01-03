package com.meam.kaffa.gateway.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class HelloController {
    @GetMapping("hello")
    fun hello(): String = "hello"
}