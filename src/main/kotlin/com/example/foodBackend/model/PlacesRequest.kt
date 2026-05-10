package com.example.foodBackend.model

data class PlacesRequest(
    val textQuery: String,
    val minRating: Double = 3.5,
    val openNow: Boolean = true
)