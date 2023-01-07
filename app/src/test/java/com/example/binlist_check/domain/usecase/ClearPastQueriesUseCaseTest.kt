package com.example.binlist_check.domain.usecase

import com.example.binlist_check.common.Status
import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.data.repository.TestPrevQueriesRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ClearPastQueriesUseCaseTest {
    private lateinit var clearPastQueriesUseCase: ClearPastQueriesUseCase
    private lateinit var prevQueriesRepository: TestPrevQueriesRepository

    private val cardData = CardData(
        id = 1,
        bin = 12346747,
        number = null,
        scheme = null,
        type = null,
        brand = null,
        prepaid = null,
        country = null,
        bank = null
    )

    @Before
    fun setup() {
        prevQueriesRepository = TestPrevQueriesRepository()

        clearPastQueriesUseCase = ClearPastQueriesUseCase(
            prevQueriesRepository = prevQueriesRepository,
        )
    }

    @Test
    fun `Clear repository use case`() {
        runBlocking {
            prevQueriesRepository.addQuery(cardData)
            prevQueriesRepository.addQuery(cardData)
            prevQueriesRepository.addQuery(cardData)
        }

        runBlocking {
            clearPastQueriesUseCase.execute().collect { status ->
                assertFalse(status is Status.Error)

                if (status is Status.Success) {
                    assertTrue(prevQueriesRepository.getAllQueries().isEmpty())
                }
            }
        }
    }

    @After
    fun reset() {
        prevQueriesRepository.setException(null)
    }


}