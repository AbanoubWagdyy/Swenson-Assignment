package com.swenson.data.repository

import com.swenson.data.entities.CurrencyResponse
import com.swenson.data.remote.ApiResponse

interface CurrencyRepository {
    suspend fun getCurrencyRates(): ApiResponse<CurrencyResponse>
}