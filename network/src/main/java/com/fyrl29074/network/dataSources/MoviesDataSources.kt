package com.fyrl29074.network.dataSources

import com.fyrl29074.network.api.MoviesApi
import com.fyrl29074.network.model.MovieDto

class MoviesDataSources(
    private val moviesApi: MoviesApi,
) {

    suspend fun getMoviesBy(page: Int): List<MovieDto> {
        return moviesApi.getMoviesBy(
            page = page,
        ).movies
    }

}