package com.example.bin_check.domain.usecase

import android.database.sqlite.SQLiteException
import com.example.bin_check.common.Status
import com.example.bin_check.common.error_type.ErrorType
import com.example.bin_check.data.entity.CardDataDTO
import com.example.bin_check.data.entity.toCardData
import com.example.bin_check.domain.entity.CardData
import com.example.bin_check.domain.repository.PrevQueriesRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class GetPastQueriesUseCase @Inject constructor(
    private val prevQueriesRepository: PrevQueriesRepository
){
    suspend fun execute() = flow {
        emit(Status.Loading<List<CardData>>())

        try {
            val queriesList = prevQueriesRepository.getAllQueries().map { cardDataDTO -> cardDataDTO.toCardData() }

            emit(Status.Success<List<CardData>>(data = queriesList))
        }
        catch (e: IOException) {
            emit(Status.Error<List<CardData>>(errorType = ErrorType.LoadQueriesIoError))
        }
        catch (e: SQLiteException) {
            emit(Status.Error<List<CardData>>(errorType = ErrorType.SQLiteError))
        }


    }
}