package com.fyrl29074.models.presentation

data class MovieVO(
    val id: Int,
    val name: String,
    val description: String,
    val countries: String,
    val ageRating: String,
    val year: String,
    val posterUrl: String,
    val posterPreviewUrl: String,
    val kpRating: String,
    val imdbRating: String,
    val filmCriticsRating: String,
    val russianFilmCriticsRating: String,
    val awaitRating: String,
)
