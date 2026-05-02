package com.example.network.models.response

import com.google.gson.annotations.SerializedName

data class SuccessAuthResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
)
