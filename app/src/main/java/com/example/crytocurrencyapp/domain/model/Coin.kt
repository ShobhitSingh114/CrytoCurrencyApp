package com.example.crytocurrencyapp.domain.model

import com.google.gson.annotations.SerializedName

// going to use this in code
data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
