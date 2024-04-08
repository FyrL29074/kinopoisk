package com.fyrl29074.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("rating")
    val rating: MovieRatingDto?,
    @SerializedName("reviewInfo")
    val reviewInfo: Int?,
    @SerializedName("poster")
    val poster: PosterDto?,
)
