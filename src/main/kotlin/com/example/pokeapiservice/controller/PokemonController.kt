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

    // These two functions annotated with @GetMapping are invoked whenever there's a get request
    // hitting that route (in this case, /pokemons)
    @GetMapping("/pokemons")
    @ResponseBody
    fun getPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): Map<String, List<Any>> {

        //function calls an instance method from PokeApiService and gets a return as a list of pokemon names
        val pokemonsList = pokeApiService.searchPokemons(query)

        // it then returns a Map, with a string as a key and a list of names as value, ordered by the sort
        // argument passed into it and filters the pokemons through the query
        return formattedResponse(pokemonsList, sort, false, query)
    }

    @GetMapping("/pokemons/highlight")
    fun getHighlightedPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): Map<String, List<Any>> {
        val pokemonsList = pokeApiService.searchPokemons(query)

        // Only difference from this function to the other one is the boolean value used to inform the
        // formattedResponse method that, for this route, the return should have a highlighted name
        return formattedResponse(pokemonsList, sort, true, query)
    }

    private fun formattedResponse(
            list: MutableList<String>,
            sort: String?,
            highlight: Boolean = false,
            query: String?): Map<String, List<Any>> {

        // This is defaulting the sort variable to alphabetical, case it's null
        val safeSort = (sort ?: "alphabetical").toString()

        val responseFormatter = ResponseFormatterService()
        val sortingService = SortingService()

        // This flow controller routes the sorting method
        val sortedList = if (safeSort.lowercase() == "alphabetical") {
            sortingService.alphabeticalSort(list)
        } else {
            sortingService.lengthSort(list)
        }

        // Returns standard formatting for highlight == false and higlighted formatting for
        // highlight == true
        return if (!highlight) {
            responseFormatter.formatList(sortedList)
        } else {
            responseFormatter.formatHighlightedList(sortedList, query)
        }
    }
}