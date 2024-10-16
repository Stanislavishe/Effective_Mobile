package com.example.data

import com.example.domian.EffectRepository
import jakarta.inject.Inject

class BaseRepository @Inject constructor(
    private val effectiveApi: EffectiveApi
): EffectRepository {
    override suspend fun getInfo() = effectiveApi.getInfo(ID, EXPORT)

    companion object {
        const val ID = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r"
        const val EXPORT = "download"
    }
}