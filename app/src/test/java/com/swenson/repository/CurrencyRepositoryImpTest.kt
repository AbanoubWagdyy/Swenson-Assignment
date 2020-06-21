package com.swenson.repository

import com.swenson.data.entities.CurrencyResponse
import com.swenson.data.entities.Rates
import com.swenson.data.remote.ApiResponse
import com.swenson.data.remote.ServiceApi
import com.swenson.data.repository.CurrencyRepositoryImp
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response.success

class CurrencyRepositoryImpTest {

    private lateinit var repo: CurrencyRepositoryImp
    private val mockServiceApi = mock(ServiceApi::class.java)

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
        repo = CurrencyRepositoryImp(mockServiceApi)
    }

    @Test
    fun `get Customers from remote api then returns success`() {
        runBlocking {
            val response = success(currencyRateRes)
            val deferred = async { response }

            `when`(mockServiceApi.getCurrencyRatesAsync()).thenReturn(deferred)
            val result = repo.getCurrencyRates()
            verify(mockServiceApi).getCurrencyRatesAsync()
            Assert.assertEquals(ApiResponse.Success(currencyRateRes), result)
        }
    }

    @Test
    fun `get Customers from remote api then returns failure`() {
        runBlocking {
            val response = error("Exception")
            val deferred = async { response }

            `when`(mockServiceApi.getCurrencyRatesAsync()).thenReturn(deferred)
            val result = repo.getCurrencyRates()
            verify(mockServiceApi).getCurrencyRatesAsync()
            Assert.assertEquals(ApiResponse.Error(404,"Exception"), result)
        }
    }
}