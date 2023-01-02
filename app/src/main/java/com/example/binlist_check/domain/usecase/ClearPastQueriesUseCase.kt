package com.example.binlist_check.domain.usecase

import com.example.binlist_check.common.Constants.loadQueriesIOExceptionMessage
import com.example.binlist_check.common.Status
import com.example.binlist_check.domain.repository.PrevQueriesRepository
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
            emit(Status.Error<Boolean>(message = loadQueriesIOExceptionMessage ))
        }
    }
}