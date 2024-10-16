package com.example.domian

import com.example.entity.models.FavoriteVacancy

interface DaoRepository {

    fun insertFavorite(favoriteVacancy: FavoriteVacancy)
    fun deleteFavorite(favoriteVacancy: FavoriteVacancy)
    fun getAllVacancies(): List<FavoriteVacancy>
}