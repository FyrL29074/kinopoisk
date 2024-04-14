package com.fyrl29074.models.domain

data class Movie(
    val id: Int,
    val name: String?,
    val description: String?,
    val countries: List<Country>?,
    val ageRating: Int?,
    val year: Int?,
    val poster: Poster?,
    val rating: Rating?,
)
