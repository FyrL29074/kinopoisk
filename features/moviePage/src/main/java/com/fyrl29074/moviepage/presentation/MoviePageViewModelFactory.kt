package com.fyrl29074.moviepage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fyrl29074.moviepage.domain.GetMovieByIdUseCase
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MoviePageViewModelFactory @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviePageViewModel(
            getMovieByIdUseCase
        ) as T
    }
}