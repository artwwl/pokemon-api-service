package com.example.pokeapiservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController {
    @GetMapping("/pokemons")
    fun getPokemons(): String {
        return "test"
    }
}