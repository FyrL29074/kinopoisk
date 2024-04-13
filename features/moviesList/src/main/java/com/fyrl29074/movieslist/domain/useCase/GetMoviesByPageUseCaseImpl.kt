package com.fyrl29074.movieslist.domain.useCase

import com.fyrl29074.movieslist.data.MoviesRepository
import com.fyrl29074.movieslist.domain.entity.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesByPageUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
): GetMoviesByPageUseCase {
    override suspend fun execute(
        page: Int,
        limit: Int,
        fromYear: Int?,
        toYear: Int?,
        country: String?,
        ageRating: Int?,
    ): List<Movie> {
        return moviesRepository.getMovies(
            page = page,
            limit = limit,
            fromYear = fromYear,
            toYear = toYear,
            country = country,
            ageRating = ageRating,
        )
    }
}