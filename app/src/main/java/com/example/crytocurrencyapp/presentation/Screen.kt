package com.example.crytocurrencyapp.presentation

sealed class Screen(val route: String) {
    // event class
    object CoinListScreen: Screen("coin_list_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
}