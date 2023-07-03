package com.example.pokeapiservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonResponse(@get:JsonProperty("results") val pokemons: List<Pokemon>)