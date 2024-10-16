package com.example.data

import com.example.data.data_base.EffectiveDao
import com.example.domian.DaoRepository
import com.example.entity.models.FavoriteVacancy
import jakarta.inject.Inject

class RepositoryDao @Inject constructor(
    private val effectiveDao: EffectiveDao
): DaoRepository {
    override fun insertFavorite(favoriteVacancy: FavoriteVacancy) =
        effectiveDao.insertVacancy(favoriteVacancy)

    override fun deleteFavorite(favoriteVacancy: FavoriteVacancy) =
        effectiveDao.deleteVacancy(favoriteVacancy)

    override fun getAllVacancies() = effectiveDao.getAllVacancies()
}