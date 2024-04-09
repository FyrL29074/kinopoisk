package com.fyrl29074.kinopoisk

import android.app.Application
import com.fyrl29074.kinopoisk.di.AppComponent
import com.fyrl29074.kinopoisk.di.DaggerAppComponent
import com.fyrl29074.movieslist.di.MoviesListComponent
import com.fyrl29074.movieslist.di.MoviesListComponentProvider

class App: Application(), MoviesListComponentProvider {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }

    override fun provideMoviesListComponentProvider(): MoviesListComponent {
        return appComponent.moviesListComponent().create()
    }
}