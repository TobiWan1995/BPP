package com.example.bpp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.example.bpp.*")
class StandardisierungRefactoredApplication

fun main(args: Array<String>) {
	runApplication<StandardisierungRefactoredApplication>(*args)
}
