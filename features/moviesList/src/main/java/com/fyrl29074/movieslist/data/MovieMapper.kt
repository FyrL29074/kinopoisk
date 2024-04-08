package com.fyrl29074.movieslist.data

import com.fyrl29074.movieslist.domain.entity.Movie
import com.fyrl29074.network.model.MovieDto

class MovieMapper {
    fun map(dto: MovieDto): Movie {
        return Movie(
            name = dto.name,
            description = dto.description,
            rating = dto.rating,
            reviewInfo = dto.reviewInfo,
            poster = dto.poster,
        )
    }
}