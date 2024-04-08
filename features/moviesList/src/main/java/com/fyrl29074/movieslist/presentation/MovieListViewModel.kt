package com.fyrl29074.movieslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyrl29074.movieslist.domain.useCase.GetMoviesByPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getMoviesByPageUseCase: GetMoviesByPageUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Waiting)
    val state: StateFlow<State> = _state.asStateFlow()

    fun getMovies() {
        _state.value = State.Loading
        viewModelScope.launch {
            runCatching {
                getMoviesByPageUseCase.execute(1)
            }.fold(
                onSuccess = { movies ->
                    _state.value = State.Loaded(movies)
                },
                onFailure = { e ->
                    _state.value = State.Error(e.message ?: "Unknown error")
                }
            )
        }
    }
}