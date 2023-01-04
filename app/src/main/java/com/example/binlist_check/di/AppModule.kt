package com.example.binlist_check.di

import android.content.Context
import com.example.binlist_check.common.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStringProvider(@ApplicationContext context: Context): StringProvider {
        return StringProvider(context)
    }
}