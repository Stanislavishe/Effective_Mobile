package com.example.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.DaoRepository
import com.example.entity.models.FavoriteVacancy
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel @Inject constructor(
    private val daoRepository: DaoRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteVacancy>>(emptyList())
    val favorites = _favorites.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun setFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _favorites.value = daoRepository.getAllVacancies()
            _isLoading.value = false
        }
    }

    fun insertFavorite(favoriteVacancy: FavoriteVacancy) {
        viewModelScope.launch(Dispatchers.IO) {
            daoRepository.insertFavorite(favoriteVacancy)
        }
    }

    fun deleteFavorite(favoriteVacancy: FavoriteVacancy) {
        viewModelScope.launch(Dispatchers.IO) {
            daoRepository.deleteFavorite(favoriteVacancy)
        }
    }
}