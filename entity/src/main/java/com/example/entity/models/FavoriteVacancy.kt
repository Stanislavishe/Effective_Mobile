package com.example.entity.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy")
data class FavoriteVacancy(
    val id: String? = null,
    val title: String,
    @PrimaryKey
    val responsibilities: String,
    val town: String,
    val company: String,
    val salary: String,
    val exp: String,
    val publishedDate: String
)