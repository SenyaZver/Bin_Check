package com.example.binlist_check.data.remote

import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.domain.data_source.DataSource
import java.lang.Exception


class TestDataSource: DataSource {
    private var exceptionToThrow: Exception? = null

    fun setException(e: Exception?) {
        exceptionToThrow = e
    }

    override suspend fun getCardData(bin: Long): CardData {
        if (exceptionToThrow!= null) {
            throw (exceptionToThrow!!)
        }
        return CardData(
            bin = bin,
            number = null,
            scheme = null,
            type = null,
            brand = null,
            prepaid = null,
            country = null,
            bank = null
        )
    }
}