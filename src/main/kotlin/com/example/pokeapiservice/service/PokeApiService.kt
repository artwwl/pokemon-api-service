package com.example.pokeapiservice.service

import com.example.pokeapiservice.model.PokemonResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
@Service
class PokeApiService() {
    val baseUrl = "https://pokeapi.co/api/v2/pokemon/"

    // Consumes the pokeApi API and returns a list of names
    private fun getAllPokemonsFromApi(): MutableList<String> {
        val restTemplate = RestTemplate()

        val response = restTemplate.getForObject("$baseUrl/?limit=10000", PokemonResponse::class.java)
        val list = mutableListOf<String>()
        println(response)
        response?.pokemons?.forEach {
            list.add(it.name)
        }
        return list
    }

    // Responsible for calling the private function above and filtering the data so we only have the pokemons
    // that include a substring that matches our query regex, thus concluding our search
    fun searchPokemons(query: String?): MutableList<String> {
        return if(query == null) {
            getAllPokemonsFromApi()
        } else {
            val pokemonList = getAllPokemonsFromApi()
            val regex = Regex(Regex.escape(query), RegexOption.IGNORE_CASE)

            pokemonList.filter { regex.containsMatchIn(it) }.toMutableList()
        }
    }
}