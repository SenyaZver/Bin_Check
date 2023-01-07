package com.example.bin_check.domain.usecase

import android.database.sqlite.SQLiteDiskIOException
import com.example.bin_check.common.Status
import com.example.bin_check.common.error_type.ErrorType
import com.example.bin_check.data.entity.CardDataDTO
import com.example.bin_check.data.repository.TestPrevQueriesRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPastQueriesUseCaseTest {
    private lateinit var getPastQueriesUseCase: GetPastQueriesUseCase
    private lateinit var prevQueriesRepository: TestPrevQueriesRepository

    private val cardData = CardDataDTO(
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

    @Test
    fun `SQL exception when getting past queries`() {
        prevQueriesRepository.setException(SQLiteDiskIOException())

        runBlocking {
            getPastQueriesUseCase.execute().collect { status ->
                assertFalse(status is Status.Success)

                if (status is Status.Error) {
                    assertNull(status.data)

                    assertEquals(status.errorType, ErrorType.SQLiteError)
                }

            }
        }
    }

    @After
    fun reset() {
        prevQueriesRepository.setException(null)
    }




}