package com.example.cryptoapp.model.data.dto2.detail

data class SUBREDDITS(
    val AVERAGE_COMMENTS_PER_DAY: Double,
    val AVERAGE_COMMENTS_PER_HOUR: Double,
    val AVERAGE_POSTS_PER_DAY: Double,
    val AVERAGE_POSTS_PER_HOUR: Double,
    val COMMUNITY_CREATED_AT: Int,
    val CURRENT_ACTIVE_USERS: Int,
    val LAST_UPDATED_TS: Int,
    val MAKE_3RD_PARTY_REQUEST: Int,
    val NAME: String,
    val SUBSCRIBERS: Int,
    val URL: String
)