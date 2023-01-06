package com.example.binlist_check.data.remote

import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.domain.data_source.DataSource


class TestDataSource: DataSource {


    override suspend fun getCardData(bin: Long): CardData {
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