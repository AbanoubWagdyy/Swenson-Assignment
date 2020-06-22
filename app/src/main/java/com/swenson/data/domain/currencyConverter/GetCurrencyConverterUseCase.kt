package com.swenson.data.domain.currencyConverter

import com.swenson.data.repository.CurrencyRepositoryImp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrencyConverterUseCase @Inject constructor(private val mRepo: CurrencyRepositoryImp) {

    operator fun invoke(
        amount: Double
    ): Double {
        val rates = mRepo.getCalledRates()?.rates
        val to = mRepo.getSymbol()
        if (rates != null) {
            when (to) {
                "AUD" -> {
                    return amount * rates.AUD
                }

                "BHD" -> {
                    return amount * rates.BHD
                }

                "GBP" -> {
                    return amount * rates.GBP
                }

                "INR" -> {
                    return amount * rates.INR
                }

                "KWD" -> {
                    return amount * rates.KWD
                }

                "USD" -> {
                    return amount * rates.USD
                }
            }
        }

        return 0.0
    }

    fun getSymbol() = mRepo.getSymbol()
}