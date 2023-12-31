package com.example.pokeapiservice.service

import org.springframework.stereotype.Service

@Service
class SortingService {

    // This is one of the infamous sorting algorithms, this one is a quick sort
    // It has an average Big O complexity of (n log n) because it divides the input into smaller pieces and
    // recursively reapplies itself onto those smaller pieces
    // But it has the disadvantage of having a max complexity of (n^2) if the chosen pivot is the worst possible,
    // which is not that great
    fun alphabeticalSort(pokemonList: MutableList<String>): List<String> {
        if (pokemonList.size <= 1) { // if there's only one element inside the list, returns the list since
            return pokemonList       // the sorting is done
        }

        val pivot = pokemonList[pokemonList.size / 2] // defines the pivot element inside the list as the middle element

        // variable initialization for all the needed lists
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

        // Returns the list joining all the pieces together
        // by recursively calling itself on both left and right sides
        // of the pivot until there's only one element in every child list
        return alphabeticalSort(left) + middle + alphabeticalSort(right)
    }


    // Same thing but sorted by word length
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
                pokemon.length < pivot.length -> left.add(pokemon) 
                pokemon.length == pivot.length -> middle.add(pokemon)
                else -> right.add(pokemon)
            }
        }


        return lengthSort(left) + middle + lengthSort(right)
    }
}