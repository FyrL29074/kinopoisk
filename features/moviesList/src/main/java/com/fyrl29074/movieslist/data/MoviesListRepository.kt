package com.fyrl29074.movieslist.data

import com.fyrl29074.models.data.mapper.MovieMapper
import com.fyrl29074.models.domain.Movie
import com.fyrl29074.network.dataSources.MoviesDataSources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesListRepository @Inject constructor(
    private val moviesDataSources: MoviesDataSources,
    private val movieMapper: MovieMapper,
) {

    suspend fun getMoviesByFilters(
        page: Int,
        limit: Int,
        fromYear: Int? = null,
        toYear: Int? = null,
        country: String? = null,
        ageRating: Int? = null,
    ): List<Movie> {
        return moviesDataSources
            .getMoviesByFilters(
                page = page,
                limit = limit,
                fromYear = fromYear,
                toYear = toYear,
                country = country,
                ageRating = ageRating,
            ).map { movieDto ->
                movieMapper.map(movieDto)
            }
    }

    suspend fun getMoviesByName(
        name: String,
        page: Int,
        limit: Int,
    ): List<Movie> {
        return moviesDataSources
            .getMovieByName(
                name = name,
                page = page,
                limit = limit
            ).map { movieDto ->
                movieMapper.map(movieDto)
            }
    }
}