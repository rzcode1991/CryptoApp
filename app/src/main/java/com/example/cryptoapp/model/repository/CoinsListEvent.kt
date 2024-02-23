package com.example.cryptoapp.model.repository

import com.example.cryptoapp.model.data.dto2.list.CoinInfo

sealed class CoinsListEvent {

    data class OnCoinClick(val coinInfo: CoinInfo): CoinsListEvent()

}