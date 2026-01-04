package org.example.movieapplibrary.domain.utils

import org.example.movieapplibrary.domain.model.moviedetails.MovieDetail

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String? = null
)