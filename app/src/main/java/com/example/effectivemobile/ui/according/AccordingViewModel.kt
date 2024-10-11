package com.example.effectivemobile.ui.according

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.entity.EffectiveDao
import com.example.effectivemobile.data.entity.FavoriteVacancy
import com.example.effectivemobile.data.models.Vacancy
import com.example.effectivemobile.use_case.UseCase
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AccordingViewModel @Inject constructor(
    private val useCase: UseCase,
    private val effectiveDao: EffectiveDao
) : ViewModel() {
    private val _vacancyInfo = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancyInfo = _vacancyInfo.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun getVacancyInfo() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _vacancyInfo.value = useCase.getInfo().vacancies
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