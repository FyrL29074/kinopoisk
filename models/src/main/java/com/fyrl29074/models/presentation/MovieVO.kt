package com.fyrl29074.models.presentation

data class MovieVO(
    val id: Int,
    val name: String,
    val description: String,
    val countries: String,
    val ageRating: String,
    val year: String,
    val posterPreviewUrl: String?
)
