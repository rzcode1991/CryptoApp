package com.example.cryptoapp.model.network

import com.example.cryptoapp.model.data.dto2.list.AllCoinsJson
import com.example.cryptoapp.model.data.dto2.detail.CoinFullDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {


    @GET("/data/top/mktcapfull?limit=100&tsym=USD")
    suspend fun getAllCoins(
        @Header("authorization") apiKey: String
    ): Response<AllCoinsJson>

    @GET("/asset/v1/data/by/symbol")
    suspend fun getCoinBySymbol(
        @Header("authorization") apiKey: String,
        @Query("asset_symbol") symbol: String
    ): Response<CoinFullDetail>

}