package com.fyrl29074.models.data.mapper

import com.fyrl29074.models.domain.Poster
import com.fyrl29074.network.model.PosterDto
import javax.inject.Inject

class PosterMapper @Inject constructor() {
    fun map(dto: PosterDto): Poster {
        return Poster(
            url = dto.url,
            previewUrl = dto.previewUrl,
        )
    }
}