package com.fyrl29074.network.dataSources

import com.fyrl29074.network.api.MoviesApi
import com.fyrl29074.network.model.MovieDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesDataSources @Inject constructor(
    private val moviesApi: MoviesApi,
) {

    suspend fun getMoviesBy(page: Int): List<MovieDto> {
        return moviesApi.getMoviesBy(
            page = page,
        ).movies
    }
}