package com.example.bin_check.domain.usecase

import android.database.sqlite.SQLiteException
import com.example.bin_check.common.Status
import com.example.bin_check.common.error_type.ErrorType
import com.example.bin_check.data.entity.toCardData
import com.example.bin_check.domain.data_source.DataSource
import com.example.bin_check.domain.entity.CardData
import com.example.bin_check.domain.repository.PrevQueriesRepository
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

            val cardDataDTO = dataSource.getCardData(bin)
            prevQueriesRepository.addQuery(cardDataDTO)

            val cardData = cardDataDTO.toCardData()
            emit(Status.Success<CardData>(data = cardData))
        }
        catch (e: HttpException) {
            emit(Status.Error<CardData>(errorType = ErrorType.HttpError))
        }
        catch (e: IOException) {
            emit(Status.Error<CardData>(errorType = ErrorType.LoadCardDataIoError))
        }catch (e: SQLiteException) {
            emit(Status.Error<CardData>(errorType = ErrorType.SQLiteError))
        }

    }
}