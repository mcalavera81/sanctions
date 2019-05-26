package com.example.sanction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SanctionProperties::class)
class SanctionApplication

fun main(args: Array<String>) {
	runApplication<SanctionApplication>(*args)
}
