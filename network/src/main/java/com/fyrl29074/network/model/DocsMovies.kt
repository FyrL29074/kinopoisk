package com.fyrl29074.network.model

import com.google.gson.annotations.SerializedName

data class DocsMovies(
    @SerializedName("docs")
    val movies: List<MovieDto>,
)
