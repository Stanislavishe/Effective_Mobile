package com.example.effectivemobile.use_case

import com.example.effectivemobile.data.Repository
import jakarta.inject.Inject

class UseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getInfo() = repository.getInfo()
}