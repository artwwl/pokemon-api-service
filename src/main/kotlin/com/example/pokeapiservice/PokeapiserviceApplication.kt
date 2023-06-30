package com.example.pokeapiservice

import com.example.pokeapiservice.service.PokeApiService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


// This runs our application :)
@SpringBootApplication
class PokeapiserviceApplication

fun main(args: Array<String>) {
	runApplication<PokeapiserviceApplication>(*args)
}
