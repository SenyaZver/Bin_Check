package com.example.bin_check.domain.usecase

import android.database.sqlite.SQLiteException
import com.example.bin_check.common.Status
import com.example.bin_check.common.error_type.ErrorType
import com.example.bin_check.data.entity.CardData
import com.example.bin_check.domain.repository.PrevQueriesRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class ClearPastQueriesUseCase @Inject constructor(
    private val prevQueriesRepository: PrevQueriesRepository
){
    suspend fun execute() = flow {
        emit(Status.Loading<Boolean>())

        try {
            prevQueriesRepository.clear()
            emit(Status.Success<Boolean>(data = true))
        } catch (e: IOException) {
            emit(Status.Error<Boolean>(errorType = ErrorType.LoadQueriesIoError))
        }catch (e: SQLiteException) {
            emit(Status.Error<CardData>(errorType = ErrorType.SQLiteError))
        }
    }
}