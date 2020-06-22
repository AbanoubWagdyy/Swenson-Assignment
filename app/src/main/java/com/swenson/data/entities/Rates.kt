package com.swenson.data.entities

import com.swenson.R

data class Rates(
    val AUD: Double,
    val BHD: Double,
    val GBP: Double,
    val INR: Double,
    val KWD: Double,
    val USD: Double
) {

    fun toCurrencyRateList(): MutableList<CurrencyRate> {
        val currencyRateList = mutableListOf<CurrencyRate>()

        for (i in 0 until 6) {
            when (i) {
                0 -> {
                    currencyRateList.add(
                        CurrencyRate(
                            AUD,
                            R.mipmap.aud,
                            "AUD"
                        )
                    )
                }

                1 -> {
                    currencyRateList.add(
                        CurrencyRate(
                            BHD,
                            R.mipmap.bhd,
                            "BHD"
                        )
                    )
                }

                2 -> {
                    currencyRateList.add(
                        CurrencyRate(
                            GBP,
                            R.mipmap.gbp,
                            "GBP"
                        )
                    )
                }

                3 -> {
                    currencyRateList.add(
                        CurrencyRate(
                            INR,
                            R.mipmap.inr,
                            "INR"
                        )
                    )
                }

                4 -> {
                    currencyRateList.add(
                        CurrencyRate(
                            KWD,
                            R.mipmap.kwd,
                            "KWD"
                        )
                    )
                }

                5 -> {
                    currencyRateList.add(
                        CurrencyRate(
                            USD,
                            R.mipmap.usd,
                            "USD"
                        )
                    )
                }
            }
        }

        return currencyRateList
    }
}