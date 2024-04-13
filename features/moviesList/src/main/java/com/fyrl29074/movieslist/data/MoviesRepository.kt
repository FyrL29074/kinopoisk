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

    // Используется поиск по name, если он null, то поиск по fromYear, toYear, country, ageRating
    suspend fun getMovies(
        page: Int,
        limit: Int,
        name: String? = null,
        fromYear: Int? = null,
        toYear: Int? = null,
        country: String? = null,
        ageRating: Int? = null,
    ): List<Movie> {
        return moviesDataSources
            .getMoviesBy(
                page = page,
                limit = limit,
                name = name,
                fromYear = fromYear,
                toYear = toYear,
                country = country,
                ageRating = ageRating,
            ).map { movieDto ->
                movieMapper.map(movieDto)
            }
    }
}