package com.meam.kaffa.ums

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class UmsApplication

fun main(args: Array<String>) {
	runApplication<UmsApplication>(*args)
}
