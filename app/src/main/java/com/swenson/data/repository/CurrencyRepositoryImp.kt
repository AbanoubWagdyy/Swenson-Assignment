package com.swenson.data.repository

import androidx.annotation.VisibleForTesting
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

    private var symbolText: String? = null
    private var currencyRates: CurrencyResponse? = null

    override suspend fun getCurrencyRates(): ApiResponse<CurrencyResponse> {
        return safeApiCall(
            call = { getRates() },
            errorMessage = "Exception occurred!"
        )
    }

    private suspend fun getRates(): ApiResponse<CurrencyResponse> {
        val result = apiServiceApi.getCurrencyRatesAsync().await()
        if (result.isSuccessful) {
            currencyRates = result.body()
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

    fun getCalledRates(): CurrencyResponse? {
        return currencyRates
    }

    @VisibleForTesting
    fun setCurrencyRates(currencyRateRes: CurrencyResponse) {
        this.currencyRates = currencyRateRes
    }

    override fun setSymbol(symbolText: String) {
        this.symbolText = symbolText
    }

    override fun getSymbol(): String? {
        return this.symbolText
    }
}