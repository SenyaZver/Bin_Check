package com.example.binlist_check.di

import android.content.Context
import androidx.room.Room
import com.example.binlist_check.common.Constants.DATABASE_NAME
import com.example.binlist_check.data.database.PastQueriesDAO
import com.example.binlist_check.data.database.PastQueriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePastQueriesDAO(database: PastQueriesDatabase): PastQueriesDAO {
        return database.pastQueriesDAO()
    }

    @Provides
    @Singleton
    fun providePastQueriesDatabase(@ApplicationContext context: Context): PastQueriesDatabase {
        return Room.databaseBuilder(context, PastQueriesDatabase::class.java, DATABASE_NAME).build()
    }
}