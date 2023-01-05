package com.example.binlist_check

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.binlist_check.data.database.PastQueriesDAO
import com.example.binlist_check.data.database.PastQueriesDatabase
import com.example.binlist_check.data.entity.CardData
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class HistoryDataBaseUnitTest {
    private lateinit var dao: PastQueriesDAO
    private lateinit var database: PastQueriesDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, PastQueriesDatabase::class.java).build()
        dao = database.pastQueriesDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun addQuery_SizeTest(){
        runBlocking{
            val cardData = CardData(
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

            dao.insertQuery(cardData)

            val pastQueries = dao.getAllQueries()

            assertEquals(pastQueries.size, 1)
        }
    }


    @Test
    @Throws(Exception::class)
    fun addMultipleQueries() {
        runBlocking{
            val cardData1 = CardData(
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
            val cardData2 = CardData(
                id = 2,
                bin = 12757858,
                number = null,
                scheme = null,
                type = null,
                brand = null,
                prepaid = null,
                country = null,
                bank = null
            )
            val cardData3 = CardData(
                id = 3,
                bin = 12757047,
                number = null,
                scheme = null,
                type = null,
                brand = null,
                prepaid = null,
                country = null,
                bank = null
            )

            dao.insertQuery(cardData1)
            dao.insertQuery(cardData2)
            dao.insertQuery(cardData3)

            val pastQueries = dao.getAllQueries()
            val resultList = listOf(cardData1, cardData2, cardData3)

            for (i in pastQueries.indices) {
                assertEquals(pastQueries[i], resultList[i])
            }
            assertEquals(pastQueries.size, 3)
        }
    }


}