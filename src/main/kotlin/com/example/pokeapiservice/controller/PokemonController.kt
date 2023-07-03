package com.example.pokeapiservice.controller

import com.example.pokeapiservice.service.CachingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(private val cachingService: CachingService) {

    @GetMapping("/pokemons")
    @ResponseBody
    fun getPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): Map<String, List<Any>>? {
        return cachingService.cache(query, sort, false)
    }

    @GetMapping("/pokemons/highlight")
    fun getHighlightedPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): Map<String, List<Any>>? {
        return cachingService.cache(query, sort, true)
    }
}