package com.example.foodBackend.controller

import com.example.foodBackend.model.CachedPlaces
import com.example.foodBackend.model.Place
import com.example.foodBackend.model.PlacesRequest
import com.example.foodBackend.model.PlacesResponse
import com.example.foodBackend.repository.Constants
import com.example.foodBackend.repository.Repository
import com.example.foodBackend.repository.LocalRepository
import com.example.foodBackend.service.Client
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.temporal.ChronoUnit

@RestController
class PlacesController(private val cachedPlacesRepository: LocalRepository) {
    @Value($$"${google.api.key}")
    lateinit var apiKey: String

    @PostMapping("/getPlaces")
    suspend fun getPlaces(@RequestBody request: PlacesRequest): ResponseEntity<PlacesResponse> {
        return try {
            val repository = Repository(Client)

            val cachedResponse = withContext(Dispatchers.IO) {cachedPlacesRepository.findById(request.textQuery.lowercase())}.orElse(null)

            if (cachedResponse != null && ChronoUnit.DAYS.between(Instant.ofEpochMilli(cachedResponse.timeStamp), Instant.now()) < Constants.CACHED_TIMER) {
                return ResponseEntity.ok(PlacesResponse(null, "OK", Gson().fromJson(cachedResponse.jsonResponse, object: TypeToken<List<Place>>() {}.type)))
            }

            val response = repository.getPlaces(apiKey, request.textQuery)

            if (response.body?.places.isNullOrEmpty()) {
                ResponseEntity.status(500).body(PlacesResponse("No Restaurants Found.", "ERROR", null))
            }

            withContext(Dispatchers.IO) {
                cachedPlacesRepository.save(
                    CachedPlaces(
                        request.textQuery.lowercase(),
                        Gson().toJson(response.body?.filterPlaces()),
                        System.currentTimeMillis()
                    )
                )
            }

            return ResponseEntity.ok(PlacesResponse(null, "OK", response.body?.filterPlaces()))
        } catch (e: Exception) {
            return ResponseEntity.status(500).body(PlacesResponse(e.localizedMessage, "ERROR", null))
        }
    }
}