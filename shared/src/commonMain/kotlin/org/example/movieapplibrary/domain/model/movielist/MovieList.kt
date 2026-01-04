package org.example.movieapplibrary.domain.model.movielist

import org.example.movieapplibrary.domain.model.moviedetails.Result

data class MovieList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)