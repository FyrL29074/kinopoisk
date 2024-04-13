package com.fyrl29074.moviepage.di

import com.fyrl29074.moviepage.presentation.MoviePageFragment
import com.fyrl29074.network.di.NetworkModule
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NetworkModule::class,
    ]
)
interface MoviePageComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MoviePageComponent
    }

    fun inject(moviePageFragment: MoviePageFragment)
}