package com.example.effectivemobile.data

import jakarta.inject.Inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository @Inject constructor() {

    suspend fun getInfo() = retrofit.getInfo(ID, EXPORT)

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(Api::class.java)

        const val ID = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r"
        const val EXPORT = "download"
    }
}