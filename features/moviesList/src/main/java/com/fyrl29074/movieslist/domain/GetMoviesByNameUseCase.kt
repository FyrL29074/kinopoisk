package com.fyrl29074.movieslist.domain

import com.fyrl29074.movieslist.data.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesByNameUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    suspend fun execute(
        name: String,
        page: Int,
        limit: Int,
    ) = moviesRepository.getMoviesByName(
        name = name,
        page = page,
        limit = limit
    )
}