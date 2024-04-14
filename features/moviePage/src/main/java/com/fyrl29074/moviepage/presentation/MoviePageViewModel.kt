package com.fyrl29074.moviepage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyrl29074.models.presentation.formatter.MovieFormatter
import com.fyrl29074.moviepage.domain.GetMovieByIdUseCase
import com.fyrl29074.moviepage.domain.GetMovieReviewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviePageViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val movieFormatter: MovieFormatter,
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Waiting)
    val state = _state.asStateFlow()

    fun getMovieById(movieId: Int) {
        _state.value = State.Loading
        viewModelScope.launch {
            runCatching {
                getMovieByIdUseCase.execute(movieId)
            }.fold(
                onSuccess = { movie ->
                    _state.value = State.Loaded(movieFormatter.format(movie))
                },
                onFailure = { e ->
                    _state.value = State.Error(e.message ?: "Unknown error")
                }
            )
        }
    }
}