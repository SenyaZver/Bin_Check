package com.example.binlist_check.data.repository

import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.domain.repository.PrevQueriesRepository

class TestPrevQueriesRepository : PrevQueriesRepository {
    private val queries = mutableListOf<CardData>()

    override suspend fun getAllQueries(): List<CardData> {
        return queries
    }

    override suspend fun addQuery(cardData: CardData) {
        queries.add(cardData)
    }

    override suspend fun clear() {
        queries.clear()
    }
}