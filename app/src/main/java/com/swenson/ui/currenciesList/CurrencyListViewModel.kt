package com.swenson.ui.currenciesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swenson.data.remote.ApiResponse
import com.swenson.data.domain.currenciesList.GetCurrencyListUseCase
import com.swenson.data.entities.CurrencyRate
import kotlinx.coroutines.*
import javax.inject.Inject

class CurrencyListViewModel
@Inject constructor(private val mUseCase: GetCurrencyListUseCase) : ViewModel() {

    private val _currencyResponse = MutableLiveData<List<CurrencyRate>>()
    val currencyResponse: LiveData<List<CurrencyRate>>
        get() = _currencyResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var job: Job? = null

    init {
        getLatestCurrencyRates()
    }

    private fun getLatestCurrencyRates() {
        _isLoading.value = true
        job = CoroutineScope(Dispatchers.Main).launch {
            val result = mUseCase()
            _isLoading.value = false
            when (result) {
                is ApiResponse.Success -> {
                    val currencyRates = result.items?.rates?.toCurrencyRateList()
                    _currencyResponse.postValue(currencyRates)
                }
                is ApiResponse.Error -> {
                    _error.postValue(result.errorMessage)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun setItemSymbol(symbolText: String) {
        mUseCase.setSymbol(symbolText)
    }
}