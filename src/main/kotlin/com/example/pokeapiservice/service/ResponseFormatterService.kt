package com.example.pokeapiservice.service
import com.example.pokeapiservice.model.HighlightedPokemon
import org.springframework.stereotype.Service

@Service
class ResponseFormatterService {

    // This first method is very simple, it takes the list of pokemon names as an argument and returns
    // a HashMap or Object containing "result" as a key and the list as a value
    fun formatList(list: List<String>): Map<String, List<String>> {
        return mapOf("result" to list)
    }

    // This other function is a bit different, it takes in a list of names and a query as arguments
    fun formatHighlightedList(list: List<String>, query: String?): Map<String, List<HighlightedPokemon>> {
        val highlighterService = HighlighterService()

        // This initializes the new list variable, we need it because, for some reason, Kotlin doesn't allow us
        // to mess with the values from argument variables
        var mutableList: MutableList<HighlightedPokemon> = emptyList<HighlightedPokemon>().toMutableList()

        // So for each name in our PokeList, we're making sure that either there is a param query or the query is
        // the current name we're iterating over, so the highlighting doesn't get all glitchy on us.
        list.forEach {

            val notNullQuery = query ?: it

            // And then we add to the list an instance of the HighlightedPokemon, its name is, well, its name
            // and its highlighted name is the return value from the highlight instance method from the HighlighterService
            mutableList.add(HighlightedPokemon(it, highlighterService.highlight(it, notNullQuery)))
        }

        return mapOf("result" to mutableList)
    }
}