package com.example.effectivemobile.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "vacancy")
data class VacancyFromDB(
    val id: Int,
    val title: String,
    val town: String,
    val company: String,
    val salary: String,
    val isFavorite: Boolean
)