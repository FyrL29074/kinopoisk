package com.fyrl29074.movieslist.domain.useCase

import com.fyrl29074.movieslist.data.MovieRepository
import com.fyrl29074.movieslist.domain.entity.Movie

class GetMoviesByPageUseCaseImpl(
    private val movieRepository: MovieRepository,
): GetMoviesByPageUseCase {
    override suspend fun execute(page: Int): List<Movie> {
        return movieRepository.getMovies(page)
    }
}