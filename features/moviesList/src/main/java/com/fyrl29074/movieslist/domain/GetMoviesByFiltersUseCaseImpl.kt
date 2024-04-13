package com.fyrl29074.movieslist.domain

import com.fyrl29074.models.domain.Movie
import com.fyrl29074.movieslist.data.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesByFiltersUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    // Используется поиск по name, если он null, то поиск по fromYear, toYear, country, ageRating
    suspend fun execute(
        page: Int,
        limit: Int,
        fromYear: Int?,
        toYear: Int?,
        country: String?,
        ageRating: Int?,
    ): List<Movie> {
        return moviesRepository.getMoviesByFilters(
            page = page,
            limit = limit,
            fromYear = fromYear,
            toYear = toYear,
            country = country,
            ageRating = ageRating,
        )
    }
}