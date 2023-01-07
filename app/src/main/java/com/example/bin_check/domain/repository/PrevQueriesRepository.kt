package com.example.bin_check.domain.repository

import com.example.bin_check.data.entity.CardDataDTO

interface PrevQueriesRepository {

    suspend fun getAllQueries(): List<CardDataDTO>

    suspend fun addQuery(cardData: CardDataDTO)

    suspend fun clear()
}