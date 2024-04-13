package com.fyrl29074.movieslist.domain.useCase

import com.fyrl29074.movieslist.domain.entity.Movie

interface GetMoviesByPageUseCase {

    suspend fun execute(
        page: Int,
        limit: Int,
        fromYear: Int? = null,
        toYear: Int? = null,
        country: String? = null,
        ageRating: Int? = null,
    ): List<Movie>
}