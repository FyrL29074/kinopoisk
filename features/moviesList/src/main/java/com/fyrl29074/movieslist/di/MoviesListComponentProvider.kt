package com.fyrl29074.movieslist.di

import dagger.Module

@Module(subcomponents = [MoviesListComponent::class])
interface MoviesListComponentProvider {

    fun provideMoviesListComponentProvider(): MoviesListComponent
}
