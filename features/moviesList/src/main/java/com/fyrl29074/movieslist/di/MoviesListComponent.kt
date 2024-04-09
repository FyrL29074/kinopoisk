package com.fyrl29074.movieslist.di

import com.fyrl29074.movieslist.presentation.MovieListFragment
import com.fyrl29074.network.di.NetworkModule
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NetworkModule::class,
    ]
)
interface MoviesListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MoviesListComponent
    }

    fun inject(moviesListFragment: MovieListFragment)
}