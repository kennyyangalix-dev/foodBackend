package com.example.foodBackend.repository

import com.example.foodBackend.model.PlacesRequest
import com.example.foodBackend.model.PlacesResponse
import com.example.foodBackend.service.PlacesApiService
import org.springframework.http.ResponseEntity

class Repository(private val placesApiService: PlacesApiService) {
    suspend fun getPlaces(apiKey: String, query: String): ResponseEntity<PlacesResponse> {
        return try {
            val response = placesApiService.searchPlaces(apiKey, Constants.FIELD_MASK, PlacesRequest(query))
            ResponseEntity.ok(response)
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }
}