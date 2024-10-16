package com.example.data.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entity.models.FavoriteVacancy

@Database(
    entities = [FavoriteVacancy::class],
    version = 1
)
abstract class EffectiveDatabase: RoomDatabase() {
    abstract fun effectiveDao(): EffectiveDao
}