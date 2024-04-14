package com.fyrl29074.models.data.mapper

import com.fyrl29074.models.domain.Rating
import com.fyrl29074.network.model.RatingDto
import javax.inject.Inject

class RatingMapper @Inject constructor() {
    fun map(dto: RatingDto): Rating {
        return Rating(
            kp = dto.kp,
            imdb = dto.imdb,
            filmCritics = dto.filmCritics,
            russianFilmCritics = dto.russianFilmCritics,
            await = dto.await,
        )
    }
}