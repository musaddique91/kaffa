package com.meam.kaffa.bootadmin

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
@EnableScheduling
class BootAdminApplication

fun main(args: Array<String>) {
	runApplication<BootAdminApplication>(*args)
}
