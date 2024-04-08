package com.fyrl29074.movieslist.presentation

import com.fyrl29074.movieslist.domain.entity.Movie

sealed class State {

    data object Waiting : State()
    data object Loading : State()
    data class Loaded(val data: List<Movie>) : State()
    data class Error(val message: String) : State()
}