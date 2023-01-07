package com.example.bin_check.data.repository

import com.example.bin_check.data.database.PastQueriesDAO
import com.example.bin_check.data.entity.CardDataDTO
import com.example.bin_check.domain.repository.PrevQueriesRepository
import javax.inject.Inject


class PrevQueriesRepositoryImpl @Inject constructor(
    private val pastQueriesDAO: PastQueriesDAO
): PrevQueriesRepository{

    override suspend fun getAllQueries(): List<CardDataDTO> {
        return pastQueriesDAO.getAllQueries()
    }


    override suspend fun addQuery(cardData: CardDataDTO) {
        pastQueriesDAO.insertQuery(cardData)
    }

    override suspend fun clear() {
        pastQueriesDAO.clear()
    }

}