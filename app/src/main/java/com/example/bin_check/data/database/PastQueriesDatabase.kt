package com.example.bin_check.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bin_check.data.entity.CardDataDTO


@Database(entities = [CardDataDTO::class], version = 1)
abstract class PastQueriesDatabase: RoomDatabase() {
    abstract fun pastQueriesDAO(): PastQueriesDAO
}