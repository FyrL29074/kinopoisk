package com.fyrl29074.network.dataSources

import com.fyrl29074.network.api.MoviesApi

class MoviesDataSources(
    private val moviesApi: MoviesApi,
) {

    suspend fun getMoviesBy(page: Int) =
        moviesApi.getMoviesBy(
            page = page,
        )
}