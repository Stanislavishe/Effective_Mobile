package com.example.effectivemobile.ui.according

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.ui.search.SearchViewModel
import jakarta.inject.Inject

class AccordingViewModelFactory @Inject constructor(
    private val accordingViewModel: AccordingViewModel
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccordingViewModel::class.java)){
            return accordingViewModel as T
        }
        throw IllegalArgumentException()
    }
}