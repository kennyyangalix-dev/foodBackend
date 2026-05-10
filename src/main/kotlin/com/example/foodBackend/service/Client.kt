package com.example.foodBackend.service

import com.example.foodBackend.model.PlacesRequest
import com.example.foodBackend.model.PlacesResponse
import com.example.foodBackend.repository.Constants
import okhttp3.OkHttpClient
import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Service
object Client: PlacesApiService {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: PlacesApiService by lazy {
        retrofit.create(PlacesApiService::class.java)
    }

    override suspend fun searchPlaces(
        apiKey: String,
        fieldMask: String,
        request: PlacesRequest
    ): PlacesResponse {
        return apiService.searchPlaces(apiKey, fieldMask, request)
    }
}