package com.example.crytocurrencyapp.data.remote.dto

import com.example.crytocurrencyapp.domain.model.Coin
import com.google.gson.annotations.SerializedName

// Dto = data transfer object

// Access all elements from api
// but doesn't use every variable
// required elements are in "domain->model"
data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}