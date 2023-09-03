package com.example.assessmenttest.module

import com.example.assessmenttest.module.models.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ImageApiService {
    @GET("/s/Njedq4WpjWz4KKk/download")
    suspend fun getImages(): Response<ResponseModel>
}