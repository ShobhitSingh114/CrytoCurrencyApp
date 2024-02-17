package com.example.crytocurrencyapp.domain.model

import com.example.crytocurrencyapp.data.remote.dto.Tag
import com.example.crytocurrencyapp.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)
