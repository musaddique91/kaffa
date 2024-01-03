package com.meam.kaffa.admin

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration

@TestConfiguration(proxyBeanMethods = false)
class TestAdminApplication

fun main(args: Array<String>) {
    fromApplication<AdminApplication>().with(TestAdminApplication::class.java).run(*args)
}
