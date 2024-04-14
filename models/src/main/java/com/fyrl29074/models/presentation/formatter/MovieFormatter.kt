package com.fyrl29074.models.presentation.formatter

import com.fyrl29074.models.domain.Movie
import com.fyrl29074.models.presentation.MovieVO
import javax.inject.Inject

class MovieFormatter @Inject constructor() {

    fun format(movie: Movie): MovieVO {

        val countries = movie.countries?.joinToString(separator = ", ") { country ->
            country.name
        } ?: "нет информации о странах"

        val kpRating =
            movie.rating?.imdb?.let { "%.1f".format(it) } ?: "Нет информации о рейтинге Кинопоиска"

        val imdbRating =
            movie.rating?.imdb?.let { "%.1f".format(it) } ?: "Нет информации о рейтинге IMDb"

        val filmCriticsRating = movie.rating?.filmCritics?.let { "%.1f".format(it) }
            ?: "Нет информации о рейтинге кинокритиков"

        val russianFilmCriticsRating = movie.rating?.russianFilmCritics?.let { "%.1f".format(it) }
            ?: "Нет информации о рейтинге российских кинокритиков"

        val awaitRating =
            movie.rating?.await?.let { "%.1f".format(it) } ?: "Нет информации о рейтинге ожидания"

        return MovieVO(
            id = movie.id,
            name = movie.name ?: "Нет информации о название",
            description = movie.description ?: "Нет информации о описании",
            countries = countries,
            ageRating = movie.ageRating?.toString() ?: "нет информации о возрастном рейтинге",
            year = movie.year?.toString() ?: "нет информации о годе",
            posterUrl = movie.poster?.url ?: "",
            posterPreviewUrl = movie.poster?.previewUrl ?: "",
            kpRating = kpRating,
            imdbRating = imdbRating,
            filmCriticsRating = filmCriticsRating,
            russianFilmCriticsRating = russianFilmCriticsRating,
            awaitRating = movie.rating?.await?.toString() ?: awaitRating,
        )
    }
}