package com.example.binlist_check.domain.usecase

import com.example.binlist_check.R
import com.example.binlist_check.common.Status
import com.example.binlist_check.common.StringProvider
import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.domain.repository.PrevQueriesRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class GetPastQueriesUseCase @Inject constructor(
    private val prevQueriesRepository: PrevQueriesRepository,
    private val stringProvider: StringProvider
){
    suspend fun execute() = flow {
        emit(Status.Loading<List<CardData>>())

        try {
            val queriesList = prevQueriesRepository.getAllQueries()

            emit(Status.Success<List<CardData>>(data = queriesList))
        } catch (e: IOException) {
            val message = stringProvider.provideString(R.string.load_queries_io_exception_message)
            emit(Status.Error<List<CardData>>(message = message))
        }


    }
}