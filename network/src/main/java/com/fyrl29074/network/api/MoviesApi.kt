package com.fyrl29074.network.api

import com.fyrl29074.network.model.DocsMovies
import com.fyrl29074.network.model.DocsReview
import com.fyrl29074.network.model.MovieDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
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
    ): DocsMovies

    @GET("movie/search")
    suspend fun getMoviesByName(
        @Header("X-API-KEY") apiKey: String = ServerInfo.API_KEY,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("query") name: String,
    ): DocsMovies

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Header("X-API-KEY") apiKey: String = ServerInfo.API_KEY,
        @Path("id") id: Int,
    ): MovieDto

    @GET("review")
    suspend fun getMovieReviews(
        @Header("X-API-KEY") apiKey: String = ServerInfo.API_KEY,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("movieId") movieId: Int,
    ): DocsReview
}