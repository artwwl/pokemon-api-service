package com.example.pokeapiservice.service

import com.example.pokeapiservice.model.Pokemon
import com.example.pokeapiservice.model.PokemonResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
@Service
class PokeApiService() {
    val baseUrl = "https://pokeapi.co/api/v2/pokemon/"

    fun getAllPokemonsFromApi(): MutableList<String> {
        val restTemplate = RestTemplate()
        val response = restTemplate.getForObject("$baseUrl/?limit=10000", PokemonResponse::class.java)
        val list = mutableListOf<String>()
        println(response)
        response?.pokemons?.forEach {
            list.add(it.name)
        }
        return list
    }
}