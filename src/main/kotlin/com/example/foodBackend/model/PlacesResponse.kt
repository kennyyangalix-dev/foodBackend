package com.example.foodBackend.model

import com.example.foodBackend.repository.Constants

data class PlacesResponse(
    var error: String? = null,
    var status: String,
    val places: List<Place>?
) {
    fun filterPlaces(): List<Place>? {
        if (!places.isNullOrEmpty()) {
            return places.filter {it.userRatingCount != null && it.userRatingCount > Constants.USERS_RATED}
        }

        return places
    }
}

data class Place(
    val types: List<String>?,
    val nationalPhoneNumber: String?,
    val formattedAddress: String?,
    val location: Coordinates?,
    val userRatingCount: Int?
)

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)