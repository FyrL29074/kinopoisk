package com.fyrl29074.movieslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fyrl29074.movieslist.domain.useCase.GetMoviesByPageUseCaseImpl
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory @Inject constructor(
    private val getMoviesByPageUseCaseImpl: GetMoviesByPageUseCaseImpl
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListViewModel(getMoviesByPageUseCaseImpl) as T
    }
}