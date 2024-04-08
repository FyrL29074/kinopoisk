package com.fyrl29074.network.model

data class MovieDto(
    val name: String?,
    val description: String?,
    val rating: MovieRatingDto?,
    val reviewInfo: Int?,
    val poster: PosterDto?,
)
