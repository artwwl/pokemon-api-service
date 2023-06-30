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
    fun getPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): Map<String, List<String>> {
        val pokemonsList = pokeApiService.searchPokemons(query)
        return formattedResponse(pokemonsList, sort)
    }

    @GetMapping("/pokemons/highlight")
    fun getHighlightedPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): String {
        return "highlighted pokemons!! query: ${query ?: "not present"} and sort: ${sort ?: "not present"}"
    }

    private fun formattedResponse(list: MutableList<String>, sort: String?): Map<String, List<String>> {
        var mutableSort = sort

        mutableSort = (if (sort == null) {
            "alphabetical"
        } else {
            sort
        }).toString()

        val responseFormatter = ResponseFormatterService()
        val sortingService = SortingService()

        val sortedList = if (mutableSort.lowercase() == "alphabetical") {
            sortingService.alphabeticalSort(list)
        } else {
            sortingService.lengthSort(list)
        }

        return responseFormatter.formatList(sortedList)
    }
}