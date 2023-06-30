package com.example.pokeapiservice.model

import com.fasterxml.jackson.annotation.JsonProperty

// very important data class used as a format for the consumption of the pokeApi data
// @get:JsonProperty("results") is used to get everything inside the "results" key inside the response from
// the pokeApi API
data class PokemonResponse(@get:JsonProperty("results") val pokemons: List<Pokemon>)