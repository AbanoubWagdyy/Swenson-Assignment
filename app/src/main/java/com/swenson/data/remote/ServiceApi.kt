package com.swenson.data.remote

import com.swenson.data.entities.CurrencyConverterResponse
import com.swenson.data.entities.CurrencyResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("latest")
    fun getCurrencyRatesAsync(
        @Query("access_key") access_key: String? = APPLICATION_ID,
        @Query("symbols") symbols: String? = SYMBOLS
    ):
            Deferred<Response<CurrencyResponse>>

    @GET("convert")
    fun convertAsync(
        @Query("access_key") access_key: String? = APPLICATION_ID,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): Deferred<Response<CurrencyConverterResponse>>
}