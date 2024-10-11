package com.example.effectivemobile.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EffectiveDao {

    @Query("SELECT * FROM vacancy")
    fun getAllVacancies(): List<FavoriteVacancy>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertVacancy(favoriteVacancy: FavoriteVacancy)

    @Delete
    fun deleteVacancy(favoriteVacancy: FavoriteVacancy)
}