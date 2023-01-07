package com.example.bin_check.data.remote

import com.example.bin_check.data.entity.CardDataDTO
import com.example.bin_check.domain.data_source.DataSource
import java.lang.Exception


class TestDataSource: DataSource {
    private var exceptionToThrow: Exception? = null

    fun setException(e: Exception?) {
        exceptionToThrow = e
    }

    override suspend fun getCardData(bin: Long): CardDataDTO {
        if (exceptionToThrow!= null) {
            throw (exceptionToThrow!!)
        }
        return CardDataDTO(
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