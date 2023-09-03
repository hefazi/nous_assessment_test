package com.example.assessmenttest.module

import com.example.assessmenttest.module.models.ImageModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageRepository {
    private var images: List<ImageModel> = emptyList()

    private val apiService: ImageApiService = Retrofit.Builder()
        .baseUrl("https://cloud.nousdigital.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ImageApiService::class.java)

    suspend fun getImages(): List<ImageModel> {
        try {
            val response = apiService.getImages()
            images = if (response.isSuccessful) {
                response.body()?.items ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
        return images

    }

    fun filterImages(query: String): List<ImageModel> {
        return images.filter {
            it.title.contains(query, true) || it.description.contains(query, true)
        }
    }
}