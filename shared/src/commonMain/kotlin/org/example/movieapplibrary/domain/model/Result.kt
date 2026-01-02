package org.example.movieapplibrary.domain.model

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
)