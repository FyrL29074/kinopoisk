package com.fyrl29074.moviepage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fyrl29074.models.presentation.formatter.MovieFormatter
import com.fyrl29074.moviepage.domain.GetMovieByIdUseCase
import com.fyrl29074.moviepage.domain.GetMovieReviewsUseCase
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MoviePageViewModelFactory @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val movieFormatter: MovieFormatter,
) : ViewModelProvider.Factory {

    var movieId = 0

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviePageViewModel(
            getMovieByIdUseCase = getMovieByIdUseCase,
            getMovieReviewsUseCase = getMovieReviewsUseCase,
            movieFormatter = movieFormatter,
            movieId = movieId
        ) as T
    }
}