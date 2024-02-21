package com.example.crytocurrencyapp.data.remote

import com.example.crytocurrencyapp.data.remote.dto.CoinDetailDto
import com.example.crytocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPeprikaApi {
    // https://api.coinpaprika.com/v1/coins
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}