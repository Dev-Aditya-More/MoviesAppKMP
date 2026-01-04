package org.example.movieapplibrary.data.repository

import org.example.movieapplibrary.domain.model.movielist.Movie
import org.example.movieapplibrary.domain.model.moviedetails.MovieDetail
import org.example.movieapplibrary.domain.model.moviesearch.MovieSearchResult

interface MoviesRepository {
    suspend fun getPopularMovies(): List<Movie>

    suspend fun getMovieDetail(movieId: Int): MovieDetail

    suspend fun searchMovies(query: String): List<MovieSearchResult>
}