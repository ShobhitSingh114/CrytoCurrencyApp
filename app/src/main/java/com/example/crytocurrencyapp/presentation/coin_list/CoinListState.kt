package com.example.crytocurrencyapp.presentation.coin_list

import com.example.crytocurrencyapp.domain.model.Coin
// state class
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
//    var coins: List<Coin> = emptyList(),
    val error: String = "",
    val searchQuery: String = ""
)
