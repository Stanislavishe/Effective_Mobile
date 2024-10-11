package com.example.effectivemobile.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.entity.EffectiveDao
import com.example.effectivemobile.data.entity.FavoriteVacancy
import com.example.effectivemobile.data.models.Offer
import com.example.effectivemobile.data.models.Vacancy
import com.example.effectivemobile.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel @Inject constructor(
    private val useCase: UseCase,
    private val effectiveDao: EffectiveDao
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
                _vacancyInfo.value = useCase.getInfo().vacancies
            } catch (e: Exception) {
                Log.d("SearchViewModel", "getVacancyInfo: $e")
            }
            finally {
                _isLoading.value = false
            }
        }
    }

    fun getRecommends(){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _recommends.value = useCase.getInfo().offers
            } catch (e: Exception) {
                Log.d("SearchViewModel", "getVacancyInfo: $e")
            } finally {
                _isLoading.value = false
            }
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