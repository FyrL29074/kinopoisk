package com.fyrl29074.models.data.mapper

import com.fyrl29074.models.domain.Review
import com.fyrl29074.network.model.ReviewDto
import javax.inject.Inject

class ReviewMapper @Inject constructor() {
    fun map(dto: ReviewDto): Review {
        return Review(
            id = dto.id,
            title = dto.title,
            type = dto.type,
            text = dto.text,
            author = dto.author,
        )
    }
}