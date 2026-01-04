package org.example.movieapplibrary.data.dto.moviesearch

import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchItemDto(
    val adult: Boolean,
    val backdrop_path: String? = null,
    val genre_ids: List<Int> = emptyList(),
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)