package com.swenson.ui.currenciesList.domain

import com.swenson.data.entities.CurrencyResponse
import com.swenson.data.remote.ApiResponse
import com.swenson.data.repository.CurrencyRepositoryImp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrencyListUseCase
@Inject constructor(private val mRepo: CurrencyRepositoryImp) {

      suspend operator fun invoke(): ApiResponse<CurrencyResponse> {
          return mRepo.getCurrencyRates()
      }
}