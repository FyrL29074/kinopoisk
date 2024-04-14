package com.fyrl29074.moviepage.data

import com.fyrl29074.models.data.mapper.MovieMapper
import com.fyrl29074.models.data.mapper.ReviewMapper
import com.fyrl29074.models.domain.Movie
import com.fyrl29074.models.domain.Review
import com.fyrl29074.network.dataSources.MoviesDataSources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePageRepository @Inject constructor(
    private val moviesDataSources: MoviesDataSources,
    private val movieMapper: MovieMapper,
    private val reviewMapper: ReviewMapper,
) {
    suspend fun getMovieById(movieId: Int): Movie {
        return movieMapper.map(moviesDataSources.getMovieById(movieId))
    }

    suspend fun getMovieReviews(
        page: Int,
        limit: Int,
        movieId: Int
    ): List<Review> {
        return moviesDataSources.getMovieReviews(
            page = page,
            limit = limit,
            movieId = movieId,
        ).map { dto ->
            reviewMapper.map(dto)
        }
    }
}