package com.example.network.models.response

import com.google.gson.annotations.SerializedName

// default pydantic validation error

data class ErrorResponse(
    @SerializedName("detail")
    val detail: List<Detail>
)

data class Detail(
    @SerializedName("loc")
    val loc: List<Any>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("input")
    val input: Any? = null,
    @SerializedName("ctx")
    val ctx: Map<String, Any>? = null
)