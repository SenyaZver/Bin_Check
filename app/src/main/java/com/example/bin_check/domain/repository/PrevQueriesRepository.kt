package com.example.bin_check.domain.repository

import com.example.bin_check.data.entity.CardData

interface PrevQueriesRepository {

    suspend fun getAllQueries(): List<CardData>

    suspend fun addQuery(cardData: CardData)

    suspend fun clear()
}