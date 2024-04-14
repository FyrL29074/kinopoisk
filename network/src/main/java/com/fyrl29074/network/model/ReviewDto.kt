package com.fyrl29074.network.model

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("review")
    val review: String,
    @SerializedName("author")
    val author: String,
)