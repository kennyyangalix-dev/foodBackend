package com.example.foodBackend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class CachedPlaces (
    @Id
    var query: String,

    @Column(columnDefinition = "TEXT")
    var jsonResponse: String,
    var timeStamp: Long
)