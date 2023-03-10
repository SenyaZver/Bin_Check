package com.example.bin_check.data.remote

import com.example.bin_check.data.remote.response.BinResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface BinApi {

    @GET("/{bin}")
    suspend fun getCardInfo( @Path("bin") bin: Long ): BinResponse


}