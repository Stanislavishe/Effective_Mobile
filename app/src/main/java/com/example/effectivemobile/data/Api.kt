package com.example.effectivemobile.data

import com.example.effectivemobile.data.models.Model
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/u/0/uc")
    suspend fun getInfo(
        @Query("id") id: String,
        @Query("export") export: String
    ): Model
}