package com.fyrl29074.kinopoisk.di

import com.fyrl29074.movieslist.di.MoviesListComponent
import com.fyrl29074.movieslist.di.MoviesListComponentProvider
import com.fyrl29074.network.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MoviesListComponentProvider::class,
        NetworkModule::class,
    ]
)
interface AppComponent {
    fun moviesListComponent(): MoviesListComponent.Factory
}