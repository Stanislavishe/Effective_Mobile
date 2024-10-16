package com.example.effectivemobile.di

import com.example.data.BaseRepository
import com.example.data.RepositoryDao
import com.example.domian.DaoRepository
import com.example.domian.EffectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(impl: BaseRepository) : EffectRepository

    @Binds
    abstract fun provideDaoRepo(impl: RepositoryDao) : DaoRepository
}