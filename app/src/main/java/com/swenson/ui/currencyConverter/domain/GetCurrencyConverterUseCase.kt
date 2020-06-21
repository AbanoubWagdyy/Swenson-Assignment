package com.swenson.ui.currencyConverter.domain

import com.swenson.data.repository.CurrencyRepositoryImp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrencyConverterUseCase @Inject constructor(private val mRepo: CurrencyRepositoryImp) {

    operator fun invoke(
        to: String,
        amount: Double
    ): Double {

        val rates = mRepo.getCalledRates()?.rates

        when (to) {
            "AUD" -> {
                return amount * rates!!.AUD
            }

            "BHD" -> {
                return amount * rates!!.BHD
            }

            "GBP" -> {
                return amount * rates!!.GBP
            }

            "INR" -> {
                return amount * rates!!.INR
            }

            "KWD" -> {
                return amount * rates!!.KWD
            }

            "USD" -> {
                return amount * rates!!.USD
            }
        }

        return 0.0
    }
}