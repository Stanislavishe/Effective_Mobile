package com.example.effectivemobile.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.models.Offer
import com.example.effectivemobile.data.models.Vacancy
import com.example.effectivemobile.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _vacancyInfo = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancyInfo = _vacancyInfo.asStateFlow()

    private val _recommends = MutableStateFlow<List<Offer>>(emptyList())
    val recommends = _recommends.asStateFlow()

    fun getVacancyInfo() {
        viewModelScope.launch {
            try {
                _vacancyInfo.value = useCase.getInfo().vacancies
            } catch (e: Exception) {
                Log.d("SearchViewModel", "getVacancyInfo: $e")
            }
        }
    }

    fun getRecommends(){
        viewModelScope.launch {
            try {
                _recommends.value = useCase.getInfo().offers
            } catch (e: Exception) {
                Log.d("SearchViewModel", "getVacancyInfo: $e")
            }
        }
    }
}