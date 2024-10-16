package com.example.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jakarta.inject.Inject

class FavoritesViewModelFactory @Inject constructor(
    private val favoritesViewModel: FavoritesViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return favoritesViewModel as T
        }
        throw IllegalArgumentException()
    }
}