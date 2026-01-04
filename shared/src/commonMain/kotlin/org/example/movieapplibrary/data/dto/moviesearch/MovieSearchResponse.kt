package org.example.movieapplibrary.data.dto.moviesearch

import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponseDto(
    val page: Int,
    val results: List<MovieSearchItemDto>,
    val total_pages: Int,
    val total_results: Int
)
