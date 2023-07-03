package com.example.pokeapiservice.service

import org.springframework.stereotype.Service
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

@Service
class CachingService(private val pokeApiService: PokeApiService) {
    fun cache(query: String?, sort: String?, highlight: Boolean): Map<String, List<Any>>? {
        val cacheKey: String
        val cache: MutableMap<String, Map<String, List<Any>>> = loadJson()

        when(query) {
            null -> {
                cacheKey = if (sort?.lowercase() == "length") {
                    "allPokemons:${sort.lowercase()}:$highlight"
                } else {
                    "allPokemons:alphabetical:$highlight"
                }
            }
            else -> {
                cacheKey = if (sort?.lowercase() == "length") {
                    "${query.lowercase()}:${sort.lowercase()}:$highlight"
                } else {
                    "${query.lowercase()}:alphabetical:$highlight"

                }
            }
        }

        return if (cache.containsKey(cacheKey)) {
            cache[cacheKey]
        } else {
            val responseFormatter = ResponseFormatterService()
            val pokemonList = pokeApiService.searchPokemons(query)
            cache[cacheKey] = responseFormatter.formattedResponse(pokemonList, sort, highlight, query)

            saveJson(cache)
            cache[cacheKey]
        }
    }

    private fun loadJson(): MutableMap<String, Map<String, List<Any>>>{
        val gson = Gson()
        val jsonFile = File("src/main/resources/cache/cache.json")
        val jsonString = jsonFile.readText()
        val mapType = object : TypeToken<MutableMap<String, Map<String, List<Any>>>>() {}.type

        return gson.fromJson(jsonString, mapType)
    }

    private fun saveJson(data: MutableMap<String, Map<String, List<Any>>>) {
        val gson = Gson()
        val jsonFile = File("src/main/resources/cache/cache.json")
        val jsonString = gson.toJson(data)

        jsonFile.writeText(jsonString)
    }
}