package com.example.binlist_check.domain.usecase

import com.example.binlist_check.R
import com.example.binlist_check.common.Status
import com.example.binlist_check.common.StringProvider
import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.domain.data_source.DataSource
import com.example.binlist_check.domain.repository.PrevQueriesRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCardDataUseCase @Inject constructor(
    private val dataSource: DataSource,
    private val prevQueriesRepository: PrevQueriesRepository,
    private val stringProvider: StringProvider
){
    suspend fun execute(bin: Long) = flow{
        try {
            emit(Status.Loading<CardData>())

            val cardData = dataSource.getCardData(bin)


            prevQueriesRepository.addQuery(cardData)

            emit(Status.Success<CardData>(data = cardData))
        }
        catch (e: HttpException) {
            val message = stringProvider.provideString( R.string.http_exception_message, e.code() )
            emit(Status.Error<CardData>(message = message))
        }
        catch (e: IOException) {
            val message = stringProvider.provideString(R.string.load_card_data_io_exception_message)
            emit(Status.Error<CardData>(message = message))
        }

    }
}