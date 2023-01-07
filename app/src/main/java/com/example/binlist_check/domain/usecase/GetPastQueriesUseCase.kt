package com.example.binlist_check.domain.usecase

import android.database.sqlite.SQLiteException
import com.example.binlist_check.common.Status
import com.example.binlist_check.common.error_type.ErrorType
import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.domain.repository.PrevQueriesRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class GetPastQueriesUseCase @Inject constructor(
    private val prevQueriesRepository: PrevQueriesRepository
){
    suspend fun execute() = flow {
        emit(Status.Loading<List<CardData>>())

        try {
            val queriesList = prevQueriesRepository.getAllQueries()

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