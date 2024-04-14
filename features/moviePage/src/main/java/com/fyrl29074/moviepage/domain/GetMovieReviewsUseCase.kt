package com.fyrl29074.moviepage.domain

import com.fyrl29074.models.domain.Review
import com.fyrl29074.moviepage.data.MoviePageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieReviewsUseCase @Inject constructor(
    private val moviePageRepository: MoviePageRepository,
) {
    suspend fun execute(
        page: Int,
        limit: Int,
        movieId: Int
    ): List<Review> {
        return moviePageRepository.getMovieReviews(page, limit, movieId)
    }
}