package com.example.foodBackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodBackendApplication

fun main(args: Array<String>) {
	runApplication<FoodBackendApplication>(*args)
}
