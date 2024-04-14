package com.fyrl29074.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("countries")
    val countries: List<CountryDto>?,
    @SerializedName("ageRating")
    val ageRating: Int?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("rating")
    val rating: RatingDto?,
    @SerializedName("poster")
    val poster: PosterDto?,
)
