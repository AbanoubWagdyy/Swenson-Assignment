package com.swenson.ui.currencyConverter.domain

import com.swenson.data.entities.CurrencyConverterResponse
import com.swenson.data.remote.ApiResponse
import com.swenson.data.repository.CurrencyRepositoryImp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrencyConverterUseCase @Inject constructor(private val mRepo: CurrencyRepositoryImp) {

    suspend operator fun invoke(
        from: String,
        to: String,
        amount: Double
    ): ApiResponse<CurrencyConverterResponse> {
        return mRepo.convert(from, to, amount)
    }
}