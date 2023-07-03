package com.example.pokeapiservice.controller

import com.example.pokeapiservice.service.CachingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(private val cachingService: CachingService) {

    // These two functions annotated with @GetMapping are invoked whenever there's a get request
    // hitting that route (in this case, /pokemons)
    @GetMapping("/pokemons")
    @ResponseBody
    fun getPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): Map<String, List<Any>>? {

        //function calls an instance method from PokeApiService and gets a return as a list of pokemon names

        // it then returns a Map, with a string as a key and a list of names as value, ordered by the sort
        // argument passed into it and filters the pokemons through the query
        return cachingService.cache(query, sort, false)
    }

    @GetMapping("/pokemons/highlight")
    fun getHighlightedPokemons(
            @RequestParam(required = false) query: String?,
            @RequestParam(required = false) sort: String?
    ): Map<String, List<Any>>? {
        // Only difference from this function to the other one is the boolean value used to inform the
        // formattedResponse method that, for this route, the return should have a highlighted name
        return cachingService.cache(query, sort, true)
    }
}