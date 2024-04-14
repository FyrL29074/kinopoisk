package com.fyrl29074.kinopoisk.di

import com.fyrl29074.models.di.ModelsComponent
import com.fyrl29074.models.di.ModelsComponentProvider
import com.fyrl29074.moviepage.di.MoviePageComponent
import com.fyrl29074.moviepage.di.MoviePageComponentProvider
import com.fyrl29074.movieslist.di.MoviesListComponent
import com.fyrl29074.movieslist.di.MoviesListComponentProvider
import com.fyrl29074.network.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MoviesListComponentProvider::class,
        MoviePageComponentProvider::class,
        ModelsComponentProvider::class,
        NetworkModule::class,
    ]
)
interface AppComponent {
    fun moviesListComponent(): MoviesListComponent.Factory

    fun moviePageComponent(): MoviePageComponent.Factory

    fun modelsComponent(): ModelsComponent.Factory
}