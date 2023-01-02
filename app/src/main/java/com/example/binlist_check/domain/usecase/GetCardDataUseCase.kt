package com.example.binlist_check.domain.usecase

import android.util.Log
import com.example.binlist_check.common.Constants.loadCardDataIOExceptionMessage
import com.example.binlist_check.common.Constants.httpExceptionMessage
import com.example.binlist_check.common.Status
import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.domain.data_source.DataSource
import com.example.binlist_check.domain.repository.PrevQueriesRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCardDataUseCase @Inject constructor(
    private val dataSource: DataSource,
    private val prevQueriesRepository: PrevQueriesRepository
){
    suspend fun execute(bin: Long) = flow{
        try {
            emit(Status.Loading<CardData>())

            val cardData = dataSource.getCardData(bin)


            prevQueriesRepository.addQuery(cardData)

            emit(Status.Success<CardData>(data = cardData))
        }
        catch (e: HttpException) {
            val message = httpExceptionMessage(e.code())
            emit(Status.Error<CardData>(message = message))
        }
        catch (e: IOException) {
            emit(Status.Error<CardData>(message = loadCardDataIOExceptionMessage))
        }

    }
}