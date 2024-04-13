package com.fyrl29074.network.dataSources

import com.fyrl29074.network.api.MoviesApi
import com.fyrl29074.network.model.MovieDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesDataSources @Inject constructor(
    private val moviesApi: MoviesApi,
) {

    suspend fun getMoviesBy(
        page: Int,
        limit: Int,
        fromYear: Int? = null,
        toYear: Int? = null,
        country: String? = null,
        ageRating: Int? = null,
    ): List<MovieDto> {
        val fromY = fromYear ?: DEFAULT_FROM_YEAR
        val toY = toYear ?: DEFAULT_TO_YEAR
        val years = "$fromY-$toY"
        return moviesApi.getMoviesBy(
            page = page,
            limit = limit,
            years = years,
            country = country,
            ageRating = ageRating,
        ).movies
    }

    companion object {
        private const val DEFAULT_FROM_YEAR = 1874
        private const val DEFAULT_TO_YEAR = 2050
    }
}