package com.fyrl29074.models.data.mapper

import com.fyrl29074.models.domain.Country
import com.fyrl29074.models.domain.Movie
import com.fyrl29074.network.model.MovieDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieMapper @Inject constructor(
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
            poster = dto.poster?.let { posterMapper.map(it) },
        )
    }
}