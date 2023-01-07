package com.example.bin_check.data.remote.data_source

import com.example.bin_check.data.entity.CardData
import com.example.bin_check.data.remote.BinApi
import com.example.bin_check.data.remote.response.toCardData
import com.example.bin_check.domain.data_source.DataSource
import javax.inject.Inject


class DataSourceImpl @Inject constructor( private val api: BinApi ): DataSource {
    override suspend fun getCardData(bin: Long): CardData {
        val response = api.getCardInfo(bin)

        return response.toCardData(bin)
    }
}