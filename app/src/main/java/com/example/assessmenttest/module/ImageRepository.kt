package com.example.assessmenttest.module

import com.example.assessmenttest.module.models.ImageModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class represents a repository for retrieving and managing images.
 * It uses an API service to fetch images from a remote server and stores them in a list.
 * The repository provides methods to get all images, filter images based on a query, and find an image by its ID.
 */
class ImageRepository {
    private var images: List<ImageModel> = emptyList()
    private val apiService: ImageApiService = Retrofit.Builder()
        .baseUrl("https://cloud.nousdigital.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ImageApiService::class.java)

    /**
     * Retrieves all images from the remote server.
     * @return A list of ImageModel objects representing the images.
     * @throws Exception if there is an error fetching the images.
     */
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

    /**
     * Filters the images based on a query string.
     * @param query The query string to filter the images.
     * @return A list of ImageModel objects that match the query.
     */
    fun filterImages(query: String): List<ImageModel> {
        return images.filter {
            it.title.contains(query, true) || it.description.contains(query, true)
        }
    }

    /**
     * Finds an image by its ID.
     * @param id The ID of the image to find.
     * @return The ImageModel object with the specified ID, or null if not found.
     */
    fun findImageById(id: Long): ImageModel? {
        return images.find {
            it.id == id
        }
    }
}