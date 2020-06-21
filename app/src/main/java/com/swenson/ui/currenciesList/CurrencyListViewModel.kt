package com.swenson.ui.currenciesList

import androidx.lifecycle.ViewModel
import com.swenson.ui.currenciesList.domain.GetCurrencyListUseCase
import javax.inject.Inject

class CurrencyListViewModel
@Inject constructor(private val mUseCase: GetCurrencyListUseCase) : ViewModel() {

}