package com.example.data.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val token: String? = null
)