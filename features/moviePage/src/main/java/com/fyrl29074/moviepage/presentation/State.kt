package com.fyrl29074.moviepage.presentation

import com.fyrl29074.models.presentation.MovieVO

sealed class State {
    data object Waiting : State()
    data object Loading : State()
    data class Loaded(val movie: MovieVO) : State()
    data class Error(val message: String) : State()
}