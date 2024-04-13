package com.fyrl29074.movieslist.data

import com.fyrl29074.movieslist.data.mapper.MovieMapper
import com.fyrl29074.movieslist.domain.entity.Movie
import com.fyrl29074.network.dataSources.MoviesDataSources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val moviesDataSources: MoviesDataSources,
    private val movieMapper: MovieMapper,
) {

    suspend fun getMovies(
        page: Int,
        limit: Int,
        fromYear: Int? = null,
        toYear: Int? = null,
        country: String? = null,
        ageRating: Int? = null,
    ): List<Movie> {
        return moviesDataSources
            .getMoviesBy(
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
}