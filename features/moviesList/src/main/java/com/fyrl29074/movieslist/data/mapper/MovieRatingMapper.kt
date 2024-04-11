package com.fyrl29074.movieslist.data.mapper

import com.fyrl29074.movieslist.domain.entity.MovieRating
import com.fyrl29074.network.model.MovieRatingDto
import javax.inject.Inject

class MovieRatingMapper @Inject constructor() {
    fun map(dto: MovieRatingDto): MovieRating {
        return MovieRating(
            kp = dto.kp,
            imdb = dto.imdb,
            filmCritics = dto.filmCritics,
            russianFilmCritics = dto.russianFilmCritics,
            await = dto.await,
        )
    }
}