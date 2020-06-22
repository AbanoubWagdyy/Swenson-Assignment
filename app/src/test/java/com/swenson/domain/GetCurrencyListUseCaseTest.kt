package com.swenson.domain

import com.swenson.data.entities.CurrencyResponse
import com.swenson.data.entities.Rates
import com.swenson.data.remote.ApiResponse
import com.swenson.data.repository.CurrencyRepositoryImp
import com.swenson.currenciesList.domain.GetCurrencyListUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.*

class GetCurrencyListUseCaseTest {

    private lateinit var useCase: GetCurrencyListUseCase
    private val mockRepo = mock(CurrencyRepositoryImp::class.java)

    private val rates = Rates(
        1.118105,
        1.637493,
        1.522172,
        4.462006,
        25.335988,
        18.08352
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
        useCase = GetCurrencyListUseCase(mockRepo)
    }

    @Test
    fun `test Get Currency List returns success`() {
        runBlocking {
            `when`(mockRepo.getCurrencyRates()).thenReturn(ApiResponse.Success(currencyRateRes))
            val result = useCase.invoke()
            verify(mockRepo).getCurrencyRates()
            Assert.assertNotNull(result)
        }
    }

    @Test
    fun `test Get Currency List returns fail`() {
        runBlocking {
            `when`(mockRepo.getCurrencyRates()).thenReturn(ApiResponse.Error(404, "error occured"))
            val result = useCase.invoke()
            verify(mockRepo).getCurrencyRates()
            Assert.assertEquals(result, ApiResponse.Error(404, "error occured"))
        }
    }
}