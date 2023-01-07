package com.example.bin_check.domain.data_source

import com.example.bin_check.data.entity.CardData

interface DataSource {
    suspend fun getCardData(bin: Long): CardData
}