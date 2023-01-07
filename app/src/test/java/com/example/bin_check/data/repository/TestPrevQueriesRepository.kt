package com.example.bin_check.data.repository

import com.example.bin_check.data.entity.CardDataDTO
import com.example.bin_check.domain.repository.PrevQueriesRepository
import java.lang.Exception

class TestPrevQueriesRepository : PrevQueriesRepository {
    private val queries = mutableListOf<CardDataDTO>()

    private var exceptionToThrow: Exception? = null

    fun setException(exception: Exception?) {
        exceptionToThrow = exception
    }

    override suspend fun getAllQueries(): List<CardDataDTO> {
        if (exceptionToThrow != null) {
            throw exceptionToThrow!!
        }

        return queries
    }

    override suspend fun addQuery(cardData: CardDataDTO) {
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