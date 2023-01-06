package com.example.binlist_check.domain.usecase

import com.example.binlist_check.common.Status
import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.data.repository.TestPrevQueriesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPastQueriesUseCaseTest {
    private lateinit var getPastQueriesUseCase: GetPastQueriesUseCase
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
        getPastQueriesUseCase = GetPastQueriesUseCase( prevQueriesRepository )

    }

    @Test
    fun `Get past queries use case`() {
        runBlocking {
            prevQueriesRepository.addQuery(cardData)
            prevQueriesRepository.addQuery(cardData)
            prevQueriesRepository.addQuery(cardData)
        }

        runBlocking {
            getPastQueriesUseCase.execute().collect { status ->
                assertFalse(status is Status.Error)

                if (status is Status.Success) {
                    assertNotNull(status.data)

                    assertEquals(status.data!!.size, 3)
                }

            }
        }


    }




}