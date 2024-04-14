package com.fyrl29074.movieslist.domain

import com.fyrl29074.movieslist.data.MoviesListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesByNameUseCase @Inject constructor(
    private val moviesListRepository: MoviesListRepository,
) {

    suspend fun execute(
        name: String,
        page: Int,
        limit: Int,
    ) = moviesListRepository.getMoviesByName(
        name = name,
        page = page,
        limit = limit
    )
}