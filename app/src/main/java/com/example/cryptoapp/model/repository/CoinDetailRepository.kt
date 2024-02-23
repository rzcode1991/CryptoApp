package com.example.cryptoapp.model.repository

import com.example.cryptoapp.model.data.dto2.detail.CoinFullDetail
import com.example.cryptoapp.model.network.ApiInterface
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class CoinDetailRepository @Inject constructor(
    @Named("detailsApiInterface") private val detailApi: ApiInterface,
    private val detailsBaseUrl: String,
    private val apiKey: String
) {

    suspend fun getCoinBySymbol(symbol: String): Response<CoinFullDetail> = detailApi.getCoinBySymbol(
        apiKey = "Apikey $apiKey",
        symbol = symbol
    )

}