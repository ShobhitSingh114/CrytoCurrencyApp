package com.example.crytocurrencyapp.data.repository

import com.example.crytocurrencyapp.data.remote.CoinPeprikaApi
import com.example.crytocurrencyapp.data.remote.dto.CoinDetailDto
import com.example.crytocurrencyapp.data.remote.dto.CoinDto
import com.example.crytocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPeprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}