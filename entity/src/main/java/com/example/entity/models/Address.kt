package com.example.entity.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    val town: String,
    val street: String,
    val house: String
)
