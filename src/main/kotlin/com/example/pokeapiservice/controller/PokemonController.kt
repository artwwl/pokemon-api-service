package com.example.pokeapiservice.controller

import com.example.pokeapiservice.model.HighlightedPokemon
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
    fun getPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): Map<String, List<Any>> {
        val pokemonsList = pokeApiService.searchPokemons(query)
        return formattedResponse(pokemonsList, sort, false, query)
    }

    @GetMapping("/pokemons/highlight")
    fun getHighlightedPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): Map<String, List<Any>> {
        val pokemonsList = pokeApiService.searchPokemons(query)
        return formattedResponse(pokemonsList, sort, true, query)
    }

    private fun formattedResponse(
            list: MutableList<String>,
            sort: String?,
            highlight: Boolean = false,
            query: String?): Map<String, List<Any>> {

        val safeSort = (sort ?: "alphabetical").toString()

        val responseFormatter = ResponseFormatterService()
        val sortingService = SortingService()

        val sortedList = if (safeSort.lowercase() == "alphabetical") {
            sortingService.alphabeticalSort(list)
        } else {
            sortingService.lengthSort(list)
        }

        return if (!highlight) {
            responseFormatter.formatList(sortedList)
        } else {
            responseFormatter.formatHighlightedList(sortedList, query)
        }
    }
}