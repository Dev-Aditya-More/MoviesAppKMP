package org.example.movieapplibrary.domain.utils

import org.example.movieapplibrary.domain.model.Movie

data class MoviesUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
    val favourites: Set<Int> = emptySet()
)