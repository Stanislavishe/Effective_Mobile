package com.example.data

import com.example.entity.models.Model
import retrofit2.http.GET
import retrofit2.http.Query

interface EffectiveApi {

    @GET("/u/0/uc")
    suspend fun getInfo(
        @Query("id") id: String,
        @Query("export") export: String
    ): Model
}