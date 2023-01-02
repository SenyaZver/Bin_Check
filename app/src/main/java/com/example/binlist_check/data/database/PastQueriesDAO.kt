package com.example.binlist_check.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.binlist_check.common.Constants.TABLE_NAME
import com.example.binlist_check.data.entity.CardData

@Dao
interface PastQueriesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(cardData: CardData)

    @Query("SELECT * FROM ${TABLE_NAME}")
    fun getAllQueries(): List<CardData>

    @Query("DELETE FROM ${TABLE_NAME}")
    fun clear()
}