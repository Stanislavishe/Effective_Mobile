package com.example.data.data_base

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entity.models.FavoriteVacancy

@Dao
interface EffectiveDao {

    @Query("SELECT * FROM vacancy")
    fun getAllVacancies(): List<FavoriteVacancy>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertVacancy(favoriteVacancy: FavoriteVacancy)

    @Delete
    fun deleteVacancy(favoriteVacancy: FavoriteVacancy)
}