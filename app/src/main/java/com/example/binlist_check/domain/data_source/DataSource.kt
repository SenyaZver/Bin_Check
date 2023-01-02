package com.example.binlist_check.domain.data_source

import com.example.binlist_check.data.entity.CardData

interface DataSource {
    suspend fun getCardData(bin: Long): CardData
}