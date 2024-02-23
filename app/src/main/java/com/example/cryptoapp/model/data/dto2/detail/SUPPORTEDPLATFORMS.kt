package com.example.cryptoapp.model.data.dto2.detail

data class SUPPORTEDPLATFORMS(
    val BLOCKCHAIN: String,
    val BRIDGE_OPERATOR: String,
    val DECIMALS: Int,
    val EXPLORER_URL: String,
    val IS_ASSET_ISSUER: Int,
    val IS_INHERITED: Int,
    val LAUNCH_DATE: Int,
    val RETIRE_DATE: Int,
    val SMART_CONTRACT_ADDRESS: String,
    val TOKEN_STANDARD: String,
    val TRADING_AS: String
)