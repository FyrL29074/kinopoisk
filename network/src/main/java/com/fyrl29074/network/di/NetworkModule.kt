package com.fyrl29074.network.di

import com.fyrl29074.network.api.MoviesApi
import com.fyrl29074.network.api.ServerInfo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideMoviesApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(ServerInfo.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }
}