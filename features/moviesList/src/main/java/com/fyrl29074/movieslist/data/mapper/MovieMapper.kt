package com.fyrl29074.movieslist.data.mapper

import com.fyrl29074.movieslist.domain.entity.Country
import com.fyrl29074.movieslist.domain.entity.Movie
import com.fyrl29074.network.model.MovieDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieMapper @Inject constructor(
    private val movieRatingMapper: MovieRatingMapper,
    private val posterMapper: PosterMapper,
) {
    fun map(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            countries = dto.countries?.map { countryDto ->
                Country(countryDto.name)
            },
            ageRating = dto.ageRating,
            year = dto.year,
            rating = dto.rating?.let { movieRatingMapper.map(it) },
            poster = dto.poster?.let { posterMapper.map(it) },
        )
    }
}