package com.example.pokeapiservice.service

import org.springframework.stereotype.Service

@Service
class SortingService {
    fun alphabeticalSort(pokemonList: MutableList<String>): List<String> {
        if (pokemonList.size <= 1) {
            return pokemonList
        }

        val pivot = pokemonList[pokemonList.size / 2]

        val left = mutableListOf<String>()
        val middle = mutableListOf<String>()
        val right = mutableListOf<String>()

        for (pokemon in pokemonList) {
            when {
                pokemon.compareTo(pivot) < 0 -> left.add(pokemon)     // Add elements less than the pivot to the left list
                pokemon.compareTo(pivot) == 0 -> middle.add(pokemon)  // Add elements equal to the pivot to the middle list
                else -> right.add(pokemon)                            // Add elements greater than the pivot to the right list
            }
        }

        return alphabeticalSort(left) + middle + alphabeticalSort(right)
    }

    fun lengthSort(pokemonList: MutableList<String>): List<String> {
        if (pokemonList.size <= 1) {
            return pokemonList
        }

        val pivot = pokemonList[pokemonList.size / 2]

        val left = mutableListOf<String>()
        val middle = mutableListOf<String>()
        val right = mutableListOf<String>()

        for (pokemon in pokemonList) {
            when {
                pokemon.length < pivot.length -> left.add(pokemon)     // Add elements less than the pivot to the left list
                pokemon.length == pivot.length -> middle.add(pokemon)   // Add elements equal to the pivot to the middle list
                else -> right.add(pokemon)                // Add elements greater than the pivot to the right list
            }
        }


        return lengthSort(left) + middle + lengthSort(right)
    }
}