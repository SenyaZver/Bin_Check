package com.example.bin_check.domain.usecase

import com.example.bin_check.common.Status
import com.example.bin_check.common.error_type.ErrorType
import com.example.bin_check.data.remote.TestDataSource
import com.example.bin_check.data.repository.TestPrevQueriesRepository
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

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
    fun `Starts loading`() {
        val bin = 12345678L

        var beganLoading = false
        runBlocking {
            getCardDataUseCase.execute(bin).collect { status ->
                if (status is Status.Loading) {
                    beganLoading = true
                } else {
                    assertTrue(beganLoading)
                }


            }
        }
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

    @Test
    fun `IO exception when getting card data`() {
        val bin = 24508558L
        dataSource.setException(IOException())

        runBlocking {
            getCardDataUseCase.execute(bin).collect { status ->
                assertFalse(status is Status.Success)

                if (status is Status.Error) {
                    assertNull(status.data)
                    assertNotNull(status.errorType)

                    assertEquals(status.errorType!!.messageRes ,ErrorType.LoadCardDataIoError.messageRes)
                }
            }
        }
    }

    @Test
    fun `Http exception when getting card data`() {
        val bin = 24508558L
        val httpException = HttpException(Response.error<ResponseBody>(500 , "some content".toResponseBody("plain/text".toMediaTypeOrNull())))
        dataSource.setException(httpException)

        runBlocking {
            getCardDataUseCase.execute(bin).collect { status ->
                assertFalse(status is Status.Success)

                if (status is Status.Error) {
                    assertNull(status.data)
                    assertNotNull(status.errorType)

                    assertTrue(status.errorType!! == ErrorType.HttpError)
                }
            }
        }
    }

    @After
    fun reset() {
        dataSource.setException(null)
        prevQueriesRepository.setException(null)
    }
}


