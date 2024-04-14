package com.fyrl29074.network.model

import com.google.gson.annotations.SerializedName

data class DocsReview(
    @SerializedName("docs")
    val reviews: List<ReviewDto>,
)
