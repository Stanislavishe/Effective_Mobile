package com.example.entity.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Button(
    val text: String
)
