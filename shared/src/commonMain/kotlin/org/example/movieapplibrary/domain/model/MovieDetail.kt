package org.example.movieapplibrary.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val rating: Double,
    val overview: String,
    val tagline: String?,
    val year: String,
    val duration: String,
    val genres: List<String>
)
