package com.fyrl29074.movieslist.presentation.model

import com.fyrl29074.movieslist.domain.entity.MovieRating

data class MovieVO(
    val id: Int,
    val name: String,
    val description: String,
    val countries: String,
    val ageRating: String,
    val year: String,
    val rating: MovieRating?,
    val posterUrl: String,
    val posterPreviewUrl: String
)
