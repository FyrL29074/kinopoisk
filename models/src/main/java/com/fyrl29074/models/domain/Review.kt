package com.fyrl29074.models.domain

data class Review(
    val id: Int,
    val author: String,
    val title: String,
    val type: String,
    val text: String,
)
