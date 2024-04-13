package com.fyrl29074.moviepage.data

import com.fyrl29074.network.dataSources.MoviesDataSources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePageRepository @Inject constructor(
    private val moviesDataSources: MoviesDataSources,
) {
    suspend fun getMovieById(movieId: Int) = moviesDataSources.getMovieById(movieId)
}