package com.example.cryptoapp.model.repository

import com.example.cryptoapp.model.data.dto2.list.AllCoinsJson
import com.example.cryptoapp.model.network.ApiInterface
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class CoinsListRepository @Inject constructor(
    @Named("listApiInterface") private val listApiInterface: ApiInterface,
    private val listBaseUrl: String,
    private val apiKey: String
) {

    suspend fun getAllCoins(): Response<AllCoinsJson> = listApiInterface.getAllCoins(
        apiKey = "Apikey $apiKey"
    )

}