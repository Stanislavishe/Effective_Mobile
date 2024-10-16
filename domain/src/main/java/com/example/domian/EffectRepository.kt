package com.example.domian

import com.example.entity.models.Model

interface EffectRepository {
    suspend fun getInfo(): Model
}