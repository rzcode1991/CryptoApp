package com.example.cryptoapp.model.data.dto2.list

data class AllCoinsJson(
    val Data: List<Data>,
    val HasWarning: Boolean,
    val Message: String,
    val MetaData: MetaData,
    val RateLimit: RateLimit,
    val SponsoredData: List<Any>,
    val Type: Int
)