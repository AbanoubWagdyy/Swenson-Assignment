package com.swenson.currenciesList

import androidx.lifecycle.ViewModel
import com.swenson.currenciesList.domain.GetCurrencyListUseCase
import javax.inject.Inject

class CurrencyListViewModel
@Inject constructor(private val mUseCase: GetCurrencyListUseCase) : ViewModel() {

}