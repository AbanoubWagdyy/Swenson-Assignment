package com.swenson.data.repository

import com.swenson.data.entities.CurrencyConverterResponse
import com.swenson.data.entities.CurrencyResponse
import com.swenson.data.remote.ApiResponse
import com.swenson.data.remote.ServiceApi
import com.swenson.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepositoryImp @Inject constructor(
    private val apiServiceApi: ServiceApi
) : CurrencyRepository {

    override suspend fun getCurrencyRates(): ApiResponse<CurrencyResponse> {
        return safeApiCall(
            call = { getRates() },
            errorMessage = "Exception occurred!"
        )
    }

    private suspend fun getRates(): ApiResponse<CurrencyResponse> {
        val result = apiServiceApi.getCurrencyRatesAsync().await()
        if (result.isSuccessful) {
            val currencyRates = result.body()
            currencyRates?.let {
                return if (it.success) {
                    ApiResponse.Success(result.body())
                } else {
                    ApiResponse.Error(result.code(), result.message())
                }
            }
        }
        return ApiResponse.Error(result.code(), result.message())
    }

    override suspend fun convert(
        from: String,
        to: String,
        amount: Double
    ): ApiResponse<CurrencyConverterResponse> {
        return safeApiCall(
            call = { getConvertedRate(from, to, amount) },
            errorMessage = "Exception occurred!"
        )
    }

    private suspend fun getConvertedRate(
        from: String,
        to: String,
        amount: Double
    ): ApiResponse<CurrencyConverterResponse> {
        val result = apiServiceApi.convertAsync(null, from, to, amount).await()
        if (result.isSuccessful) {
            val rate = result.body()
            rate?.let {
                return if (it.success) {
                    ApiResponse.Success(result.body())
                } else {
                    ApiResponse.Error(result.code(), result.message())
                }
            }
        }
        return ApiResponse.Error(result.code(), result.message())
    }
}