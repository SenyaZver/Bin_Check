package com.example.binlist_check.di

import com.example.binlist_check.data.database.PastQueriesDAO
import com.example.binlist_check.data.repository.PrevQueriesRepositoryImpl
import com.example.binlist_check.domain.repository.PrevQueriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {



    @Provides
    @Singleton
    fun providePrevQueriesRepository(pastQueriesDAO: PastQueriesDAO): PrevQueriesRepository {
        return PrevQueriesRepositoryImpl(pastQueriesDAO)
    }



}