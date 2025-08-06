package com.example.data.datasource.remote

import com.example.data.model.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = "75db466598114a3ba5b2afec9f2dee05"
    ): NewsResponseDto



}