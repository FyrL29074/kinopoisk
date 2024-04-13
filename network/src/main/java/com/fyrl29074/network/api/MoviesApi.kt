package com.fyrl29074.network.api

import com.fyrl29074.network.model.Docs
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie")
    suspend fun getMoviesBy(
        @Header("X-API-KEY") apiKey: String = ServerInfo.API_KEY,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("year") years: String? = null,
        @Query("countries.name") country: String? = null,
        @Query("ageRating") ageRating: Int? = null,
    ): Docs
}