package com.example.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.DaoRepository
import com.example.domian.EffectRepository
import com.example.entity.models.FavoriteVacancy
import com.example.entity.models.Offer
import com.example.entity.models.Vacancy
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel @Inject constructor(
    private val effectRepository: EffectRepository,
    private val daoRepository: DaoRepository
) : ViewModel() {

    private val _vacancyInfo = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancyInfo = _vacancyInfo.asStateFlow()

    private val _recommends = MutableStateFlow<List<Offer>>(emptyList())
    val recommends = _recommends.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun getVacancyInfo() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _vacancyInfo.value = effectRepository.getInfo().vacancies
            } catch (e: Exception) {
                Log.d("SearchViewModel", "getVacancyInfo: $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRecommends() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _recommends.value = effectRepository.getInfo().offers
            } catch (e: Exception) {
                Log.d("SearchViewModel", "getVacancyInfo: $e")
            } finally {
                _isLoading.value = false
            }
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