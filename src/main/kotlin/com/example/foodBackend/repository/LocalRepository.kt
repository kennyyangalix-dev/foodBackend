package com.example.foodBackend.repository

import com.example.foodBackend.model.CachedPlaces
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocalRepository: JpaRepository<CachedPlaces, String> {
}