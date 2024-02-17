package com.example.crytocurrencyapp.domain.repository

import com.example.crytocurrencyapp.data.remote.dto.CoinDetailDto
import com.example.crytocurrencyapp.data.remote.dto.CoinDto

// domain->repo
// Here we only define our Repository

// this repo Implementation is in data->repo
interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}