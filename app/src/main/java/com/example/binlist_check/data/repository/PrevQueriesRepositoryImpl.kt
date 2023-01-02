package com.example.binlist_check.data.repository

import com.example.binlist_check.data.database.PastQueriesDAO
import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.domain.repository.PrevQueriesRepository
import javax.inject.Inject


class PrevQueriesRepositoryImpl @Inject constructor(
    private val pastQueriesDAO: PastQueriesDAO
): PrevQueriesRepository{

    override suspend fun getAllQueries(): List<CardData> {
        return pastQueriesDAO.getAllQueries()
    }


    override suspend fun addQuery(cardData: CardData) {
        pastQueriesDAO.insertQuery(cardData)
    }

    override suspend fun clear() {
        pastQueriesDAO.clear()
    }

}