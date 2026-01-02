package org.example.movieapplibrary.domain.utils

import org.example.movieapplibrary.domain.model.MovieDetail

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val isFavourite: Boolean = false,
    val error: String? = null
)