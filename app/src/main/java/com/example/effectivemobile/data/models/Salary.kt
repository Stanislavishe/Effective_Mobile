package com.example.effectivemobile.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Salary(
    val full: String,
    val short: String? = null
)
