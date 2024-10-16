package com.example.effectivemobile.di

import android.content.Context
import androidx.room.Room
import com.example.data.EffectiveApi
import com.example.data.data_base.EffectiveDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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

    @Provides
    fun provideEffectiveApi() = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(EffectiveApi::class.java)
}