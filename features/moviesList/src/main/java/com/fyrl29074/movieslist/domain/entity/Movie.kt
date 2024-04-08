package com.fyrl29074.movieslist.domain.entity

import com.fyrl29074.network.model.MovieRatingDto
import com.fyrl29074.network.model.PosterDto

data class Movie(
    val name: String?,
    val description: String?,
    val rating: MovieRatingDto?,
    val reviewInfo: Int?,
    val poster: PosterDto?,
)
