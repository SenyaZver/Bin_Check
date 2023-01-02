package com.example.binlist_check.domain.repository

import com.example.binlist_check.data.entity.CardData

interface PrevQueriesRepository {

    suspend fun getAllQueries(): List<CardData>

    suspend fun addQuery(cardData: CardData)

    suspend fun clear()
}