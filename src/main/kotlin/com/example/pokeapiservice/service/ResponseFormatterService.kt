package com.example.pokeapiservice.service
import com.example.pokeapiservice.model.HighlightedPokemon
import org.springframework.stereotype.Service

@Service
class ResponseFormatterService {

    fun formattedResponse(
        list: MutableList<String>,
        sort: String?,
        highlight: Boolean = false,
        query: String?): Map<String, List<Any>> {

        val safeSort = (sort ?: "alphabetical").toString()

        val sortingService = SortingService()

        val sortedList = if (safeSort.lowercase() == "alphabetical") {
            sortingService.alphabeticalSort(list)
        } else {
            sortingService.lengthSort(list)
        }

        return if (!highlight) {
            formatList(sortedList)
        } else {
            formatHighlightedList(sortedList, query)
        }
    }

    private fun formatList(list: List<String>): Map<String, List<String>> {
        return mapOf("result" to list)
    }

    private fun formatHighlightedList(list: List<String>, query: String?): Map<String, List<HighlightedPokemon>> {
        val highlighterService = HighlighterService()

        val mutableList: MutableList<HighlightedPokemon> = emptyList<HighlightedPokemon>().toMutableList()

        list.forEach {

            val notNullQuery = query ?: it

            mutableList.add(HighlightedPokemon(it, highlighterService.highlight(it, notNullQuery)))
        }

        return mapOf("result" to mutableList)
    }
}