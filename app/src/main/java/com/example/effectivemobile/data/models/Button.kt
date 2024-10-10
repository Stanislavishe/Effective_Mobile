package com.example.effectivemobile.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Button(
    val text: String
)
