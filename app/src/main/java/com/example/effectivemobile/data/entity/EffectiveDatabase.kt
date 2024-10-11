package com.example.effectivemobile.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteVacancy::class],
    version = 1
)
abstract class EffectiveDatabase: RoomDatabase() {
    abstract fun effectiveDao(): EffectiveDao
}