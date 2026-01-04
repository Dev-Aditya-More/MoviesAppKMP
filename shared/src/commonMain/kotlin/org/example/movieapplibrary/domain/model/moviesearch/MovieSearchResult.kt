package org.example.movieapplibrary.domain.model.moviesearch

data class MovieSearchResult(
    val id: Int,
    val title: String,
    val poster_path: String,
    val vote_average: Double,
    val release_date: String
)