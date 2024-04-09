package com.fyrl29074.movieslist.data

import com.fyrl29074.movieslist.domain.entity.Movie
import com.fyrl29074.network.dataSources.MoviesDataSources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val moviesDataSources: MoviesDataSources,
    private val movieMapper: MovieMapper,
) {

    suspend fun getMovies(page: Int): List<Movie> {
        return moviesDataSources
            .getMoviesBy(page)
            .map { movieDto ->
                movieMapper.map(movieDto)
            }
    }
}