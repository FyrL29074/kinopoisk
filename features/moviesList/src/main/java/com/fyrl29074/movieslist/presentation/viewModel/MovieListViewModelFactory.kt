package com.fyrl29074.movieslist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fyrl29074.movieslist.domain.GetMoviesByFiltersUseCaseImpl
import com.fyrl29074.movieslist.domain.GetMoviesByNameUseCase
import com.fyrl29074.models.presentation.formatter.MovieFormatter
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory @Inject constructor(
    private val getMoviesByFiltersUseCaseImpl: GetMoviesByFiltersUseCaseImpl,
    private val getMoviesByNameUseCase: GetMoviesByNameUseCase,
    private val movieFormatter: MovieFormatter,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListViewModel(
            getMoviesByFiltersUseCaseImpl,
            getMoviesByNameUseCase,
            movieFormatter,
        ) as T
    }
}