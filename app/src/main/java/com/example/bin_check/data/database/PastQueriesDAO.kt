package com.example.bin_check.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bin_check.common.Constants.TABLE_NAME
import com.example.bin_check.data.entity.CardDataDTO

@Dao
interface PastQueriesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(cardData: CardDataDTO)

    @Query("SELECT * FROM ${TABLE_NAME}")
    fun getAllQueries(): List<CardDataDTO>

    @Query("DELETE FROM ${TABLE_NAME}")
    fun clear()
}