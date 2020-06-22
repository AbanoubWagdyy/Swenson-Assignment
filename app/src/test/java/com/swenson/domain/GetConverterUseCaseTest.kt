package com.swenson.domain

import com.swenson.data.entities.CurrencyResponse
import com.swenson.data.entities.Rates
import com.swenson.data.remote.ApiResponse
import com.swenson.data.repository.CurrencyRepositoryImp
import com.swenson.data.domain.currenciesList.GetCurrencyListUseCase
import com.swenson.data.domain.currencyConverter.GetCurrencyConverterUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.*

class GetConverterUseCaseTest {

    private lateinit var useCase: GetCurrencyConverterUseCase
    private val mockRepo = mock(CurrencyRepositoryImp::class.java)

    private val rates = Rates(
        1.118105,
        1.637493,
        1.522172,
        4.462006,
        25.335988,
        1.08352
    )
    private val currencyRateRes = CurrencyResponse(
        "EUR",
        "2020-06-21",
        rates,
        true,
        1592778965
    )

    @Before
    fun setUp() {
        mockRepo.setCurrencyRates(currencyRateRes)
        useCase = GetCurrencyConverterUseCase(mockRepo)
    }

    @Test
    fun `test Convert From EUR to USD returns Zero`() {
        `when`(mockRepo.getCalledRates()).thenReturn(null)
        val result = useCase.invoke("USD", 25.0)
        assertEquals(result, 0.0)
    }

    @Test
    fun `test Convert From EUR to USD returns Amount`() {
        `when`(mockRepo.getCalledRates()).thenReturn(currencyRateRes)
        val result = useCase.invoke("USD", 25.0)
        assertEquals(result, 27.088)
    }
}