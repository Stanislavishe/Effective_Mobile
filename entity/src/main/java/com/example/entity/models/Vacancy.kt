package com.example.entity.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Vacancy(
    val id: String,
    val lookingNumber: Int? = null,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String,
    val questions: List<String>
)
