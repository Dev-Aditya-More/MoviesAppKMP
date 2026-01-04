package org.example.movieapplibrary.domain.utils

import org.example.movieapplibrary.domain.model.moviesearch.MovieSearchResult

data class SearchUiState(
    val query: String = "",
    val results: List<MovieSearchResult> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
