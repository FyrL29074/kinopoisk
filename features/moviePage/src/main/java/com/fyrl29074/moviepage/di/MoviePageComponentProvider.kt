package com.fyrl29074.moviepage.di

import dagger.Module

@Module(subcomponents = [MoviePageComponent::class])
interface MoviePageComponentProvider {

    fun provideMoviePageComponent(): MoviePageComponent
}