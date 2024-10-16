package com.example.entity.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Model(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)