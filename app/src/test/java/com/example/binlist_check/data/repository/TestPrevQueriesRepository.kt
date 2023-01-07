package com.example.binlist_check.data.repository

import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.domain.repository.PrevQueriesRepository
import java.lang.Exception

class TestPrevQueriesRepository : PrevQueriesRepository {
    private val queries = mutableListOf<CardData>()

    private var exceptionToThrow: Exception? = null

    fun setException(exception: Exception?) {
        exceptionToThrow = exception
    }

    override suspend fun getAllQueries(): List<CardData> {
        if (exceptionToThrow != null) {
            throw exceptionToThrow!!
        }

        return queries
    }

    override suspend fun addQuery(cardData: CardData) {
        if (exceptionToThrow != null) {
            throw exceptionToThrow!!
        }

        queries.add(cardData)
    }

    override suspend fun clear() {
        if (exceptionToThrow != null) {
            throw exceptionToThrow!!
        }

        queries.clear()
    }
}