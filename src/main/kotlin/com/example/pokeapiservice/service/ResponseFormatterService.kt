package com.example.pokeapiservice.service
import org.springframework.stereotype.Service

@Service
class ResponseFormatterService {
    fun formatList(list: List<String>): Map<String, List<String>> {
        return mapOf("result" to list)
    }
}