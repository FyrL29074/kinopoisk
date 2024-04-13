package com.fyrl29074.movieslist.domain.useCase

import com.fyrl29074.movieslist.data.MoviesRepository
import com.fyrl29074.movieslist.domain.entity.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesByPageUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
): GetMoviesByPageUseCase {

    // Используется поиск по name, если он null, то поиск по fromYear, toYear, country, ageRating
    override suspend fun execute(
        page: Int,
        limit: Int,
        name: String?,
        fromYear: Int?,
        toYear: Int?,
        country: String?,
        ageRating: Int?,
    ): List<Movie> {
        return moviesRepository.getMovies(
            page = page,
            limit = limit,
            name = name,
            fromYear = fromYear,
            toYear = toYear,
            country = country,
            ageRating = ageRating,
        )
    }
}