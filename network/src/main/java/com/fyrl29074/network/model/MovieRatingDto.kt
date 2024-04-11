package com.fyrl29074.network.model

import com.google.gson.annotations.SerializedName

data class MovieRatingDto(
    @SerializedName("kp")
    val kp: Double?,
    @SerializedName("imdb")
    val imdb: Double?,
    @SerializedName("filmCritics")
    val filmCritics: Double?,
    @SerializedName("russianFilmCritics")
    val russianFilmCritics: Double?,
    @SerializedName("await")
    val await: Double?,
)
