package com.fyrl29074.moviepage.domain

import com.fyrl29074.moviepage.data.MoviePageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieByIdUseCase @Inject constructor(
    private val moviePageRepository: MoviePageRepository,
) {

}
