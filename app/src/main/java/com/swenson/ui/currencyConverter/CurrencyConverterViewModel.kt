package com.swenson.ui.currencyConverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swenson.data.domain.currencyConverter.GetCurrencyConverterUseCase
import javax.inject.Inject

class CurrencyConverterViewModel
@Inject constructor(private val mUseCase: GetCurrencyConverterUseCase) : ViewModel() {

    fun getSymbol(): String? = mUseCase.getSymbol()

    fun convert(amount: Double) {
        _convertedRate.postValue(mUseCase(amount))
    }


    private val _convertedRate = MutableLiveData<Double>()
    val convertedRate: LiveData<Double>
        get() = _convertedRate

    init {
        _convertedRate.postValue(mUseCase(1.0))
    }

}