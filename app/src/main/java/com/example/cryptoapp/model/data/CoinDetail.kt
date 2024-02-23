package com.example.cryptoapp.model.data

import com.example.cryptoapp.model.data.dto.Team

data class CoinDetail(
    val id: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<Team>
)