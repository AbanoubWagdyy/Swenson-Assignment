package com.swenson.data.entities

class CurrencyRate(
    val rate: Double = 0.0,
    val symbolIcon: Int,
    val symbolText : String
){
    override fun toString(): String {
        return "{rate=$rate, symbolIcon=$symbolIcon, symbolText='$symbolText'}"
    }
}