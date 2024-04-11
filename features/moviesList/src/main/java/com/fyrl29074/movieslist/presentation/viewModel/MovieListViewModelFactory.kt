package com.fyrl29074.movieslist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fyrl29074.movieslist.domain.useCase.GetMoviesByPageUseCaseImpl
import com.fyrl29074.movieslist.presentation.formatter.MovieFormatter
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory @Inject constructor(
    private val getMoviesByPageUseCaseImpl: GetMoviesByPageUseCaseImpl,
    private val movieFormatter: MovieFormatter,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListViewModel(
            getMoviesByPageUseCaseImpl,
            movieFormatter,
        ) as T
    }
}