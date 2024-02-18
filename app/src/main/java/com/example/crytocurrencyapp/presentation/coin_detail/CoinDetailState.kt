package com.example.crytocurrencyapp.presentation.coin_detail

import com.example.crytocurrencyapp.domain.model.Coin
import com.example.crytocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
