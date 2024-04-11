package com.fyrl29074.movieslist.presentation.formatter

import com.fyrl29074.movieslist.domain.entity.Movie
import com.fyrl29074.movieslist.presentation.model.MovieVO
import javax.inject.Inject

class MovieFormatter @Inject constructor() {

    fun format(movie: Movie): MovieVO {

        val countries = movie.countries?.joinToString(separator = ", ") { country ->
            country.name
        } ?: "нет информации о странах"

        return MovieVO(
            id = movie.id,
            name = movie.name ?: "Нет информации о название",
            description = movie.description ?: "Нет информации о описании",
            countries = countries,
            ageRating = movie.ageRating?.toString() ?: "нет информации о возрастном рейтинге",
            year = movie.year?.toString() ?: "нет информации о годе",
            rating = movie.rating,
            posterUrl = movie.poster?.url ?: "",
            posterPreviewUrl = movie.poster?.previewUrl ?: "",
        )
    }
}