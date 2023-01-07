package com.example.bin_check.di

import com.example.bin_check.common.Constants.BASE_URL
import com.example.bin_check.data.remote.BinApi
import com.example.bin_check.data.remote.data_source.DataSourceImpl
import com.example.bin_check.domain.data_source.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideDataSource(binApi: BinApi): DataSource {
        return DataSourceImpl(binApi)
    }

    @Provides
    @Singleton
    fun provideBinApi(): BinApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinApi::class.java)
    }


}