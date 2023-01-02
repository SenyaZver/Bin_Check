package com.example.binlist_check.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.binlist_check.data.entity.CardData


@Database(entities = [CardData::class], version = 1)
abstract class PastQueriesDatabase: RoomDatabase() {
    abstract fun pastQueriesDAO(): PastQueriesDAO
}