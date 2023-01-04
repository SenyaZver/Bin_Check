package com.example.binlist_check.domain.usecase

import com.example.binlist_check.R
import com.example.binlist_check.common.Status
import com.example.binlist_check.common.StringProvider
import com.example.binlist_check.domain.repository.PrevQueriesRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class ClearPastQueriesUseCase @Inject constructor(
    private val prevQueriesRepository: PrevQueriesRepository,
    private val stringProvider: StringProvider
){
    suspend fun execute() = flow {
        emit(Status.Loading<Boolean>())

        try {
            prevQueriesRepository.clear()
            emit(Status.Success<Boolean>(data = true))
        } catch (e: IOException) {
            val message = stringProvider.provideString(R.string.load_card_data_io_exception_message)
            emit(Status.Error<Boolean>(message = message ))
        }
    }
}