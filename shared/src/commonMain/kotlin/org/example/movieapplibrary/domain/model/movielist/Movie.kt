package org.example.movieapplibrary.domain.model.movielist

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val rating: Double,
    val year: String? = null
)