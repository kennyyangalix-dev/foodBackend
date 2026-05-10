package com.example.foodBackend.service

import com.example.foodBackend.model.PlacesRequest
import com.example.foodBackend.model.PlacesResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PlacesApiService {
    @POST("v1/places:searchText")
    suspend fun searchPlaces(
        @Header("X-Goog-Api-Key") apiKey: String,
        @Header("X-Goog-FieldMask") fieldMask: String,
        @Body request: PlacesRequest
    ): PlacesResponse
}