package com.example.pokeapiservice.service
import com.example.pokeapiservice.model.HighlightedPokemon
import org.springframework.stereotype.Service

@Service
class ResponseFormatterService {
    fun formatList(list: List<String>): Map<String, List<String>> {
        return mapOf("result" to list)
    }

    fun formatHighlightedList(list: List<String>, query: String?): Map<String, List<HighlightedPokemon>> {
        val highlighterService = HighlighterService()

        var mutableList: MutableList<HighlightedPokemon> = emptyList<HighlightedPokemon>().toMutableList()

        list.forEach {

            val notNullQuery = query ?: it

            mutableList.add(HighlightedPokemon(it, highlighterService.highlight(it, notNullQuery)))
        }

        return mapOf("result" to mutableList)
    }
}