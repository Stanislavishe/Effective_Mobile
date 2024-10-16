package com.example.entity.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Offer(
    val id: String? = null,
    val title: String,
    val button: Button? = null,
    val link: String
)
