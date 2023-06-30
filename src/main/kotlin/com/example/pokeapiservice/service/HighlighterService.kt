package com.example.pokeapiservice.service

import org.springframework.stereotype.Service

@Service
class HighlighterService {
    fun highlight(name: String, query: String): String {
        val regex = Regex(Regex.escape(query), RegexOption.IGNORE_CASE)

        return name.replace(regex, "<pre>${query.lowercase()}</pre>")
    }
}