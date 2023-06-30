package com.example.pokeapiservice.controller

import com.example.pokeapiservice.service.PokeApiService
import com.example.pokeapiservice.service.ResponseFormatterService
import com.example.pokeapiservice.service.SortingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(private val pokeApiService: PokeApiService) {
    @GetMapping("/pokemons")
    @ResponseBody
    fun getPokemons(): Map<String, List<String>> {
        val pokemonsList = pokeApiService.getAllPokemonsFromApi()
        return formattedResponse(pokemonsList)

    }

    @GetMapping("/pokemons/highlight")
    fun getHighlightedPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): String {
        return "highlighted pokemons!! query: ${query ?: "not present"} and sort: ${sort ?: "not present"}"
    }

    private fun formattedResponse(list: MutableList<String>, sort: String = "length"): Map<String, List<String>> {
        val responseFormatter = ResponseFormatterService()
        val sortingService = SortingService()
        var sortedList = emptyList<String>()
        if (sort == "alphabetical") {
            sortedList = sortingService.alphabeticalSort(list)
        } else {
            sortedList = sortingService.lengthSort(list)
        }
        return responseFormatter.formatList(sortedList)
    }
}