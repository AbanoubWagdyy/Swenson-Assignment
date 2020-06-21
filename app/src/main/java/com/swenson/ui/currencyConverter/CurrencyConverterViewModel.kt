package com.swenson.ui.currencyConverter

import androidx.lifecycle.ViewModel
import com.swenson.ui.currencyConverter.domain.GetCurrencyConverterUseCase
import javax.inject.Inject

class CurrencyConverterViewModel
@Inject constructor(private val mUseCase: GetCurrencyConverterUseCase) : ViewModel() {

}