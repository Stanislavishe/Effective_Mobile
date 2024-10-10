package com.example.effectivemobile.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Experience(
    val previewText: String,
    val text: String
)