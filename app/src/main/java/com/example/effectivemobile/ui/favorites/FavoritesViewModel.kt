package com.example.effectivemobile.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.entity.EffectiveDao
import com.example.effectivemobile.data.entity.FavoriteVacancy
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel @Inject constructor(
    private val effectiveDao: EffectiveDao
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteVacancy>>(emptyList())
    val favorites = _favorites.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun setFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _favorites.value = effectiveDao.getAllVacancies()
            _isLoading.value = false
        }
    }

    fun insertFavorite(favoriteVacancy: FavoriteVacancy) {
        viewModelScope.launch(Dispatchers.IO) {
            effectiveDao.insertVacancy(favoriteVacancy)
        }
    }

    fun deleteFavorite(favoriteVacancy: FavoriteVacancy){
        viewModelScope.launch(Dispatchers.IO) {
            effectiveDao.deleteVacancy(favoriteVacancy)
        }
    }
}