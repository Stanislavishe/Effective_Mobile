package com.example.effectivemobile.data.di

import android.content.Context
import androidx.room.Room
import com.example.effectivemobile.data.entity.EffectiveDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideEffectiveDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            EffectiveDatabase::class.java,
            "Effective Database"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideEffectiveDao(db: EffectiveDatabase) = db.effectiveDao()
}