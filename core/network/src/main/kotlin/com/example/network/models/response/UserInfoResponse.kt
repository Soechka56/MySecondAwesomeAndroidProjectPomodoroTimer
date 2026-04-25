package com.example.network.models.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("email")
    val email: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)
