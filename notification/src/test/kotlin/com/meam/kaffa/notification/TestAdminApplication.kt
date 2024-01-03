package com.meam.kaffa.notification

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration

@TestConfiguration(proxyBeanMethods = false)
class TestAdminApplication

fun main(args: Array<String>) {
    fromApplication<com.meam.kaffa.notification.NotificationApplication>().with(TestAdminApplication::class.java).run(*args)
}
