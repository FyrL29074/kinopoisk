package com.fyrl29074.movieslist.domain.entity

data class Movie(
    val id: Int,
    val name: String?,
    val description: String?,
    val countries: List<Country>?,
    val ageRating: Int?,
    val year: Int?,
    val rating: MovieRating?,
    val poster: Poster?,
)
