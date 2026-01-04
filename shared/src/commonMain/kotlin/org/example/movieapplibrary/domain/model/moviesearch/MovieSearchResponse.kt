package org.example.movieapplibrary.domain.model.moviesearch

data class MovieSearchResponse(
    val page: Int,
    val results: List<MovieSearchResult>,
    val total_pages: Int,
    val total_results: Int
)