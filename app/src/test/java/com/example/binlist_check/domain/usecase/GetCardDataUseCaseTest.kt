package com.example.binlist_check.domain.usecase

import com.example.binlist_check.common.Status
import com.example.binlist_check.data.remote.TestDataSource
import com.example.binlist_check.data.repository.TestPrevQueriesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetCardDataUseCaseTest {
    private lateinit var getCardDataUseCase: GetCardDataUseCase
    private lateinit var prevQueriesRepository: TestPrevQueriesRepository
    private lateinit var dataSource: TestDataSource

    @Before
    fun setup() {
        prevQueriesRepository = TestPrevQueriesRepository()
        dataSource = TestDataSource()
        getCardDataUseCase = GetCardDataUseCase(
            prevQueriesRepository = prevQueriesRepository,
            dataSource = dataSource
        )


    }

    @Test
    fun `Get correct card data use case`() {
        val bin = 12345678L
        runBlocking {
            getCardDataUseCase.execute(bin).collect { status ->
                assertFalse(status is Status.Error)

                if (status is Status.Success) {
                    assertNotNull(status.data)

                    assertEquals(status.data!!.bin, bin)
                }
            }
        }
    }

}