package org.example.movieapplibrary.data.repository

import org.example.movieapplibrary.domain.model.Movie
import org.example.movieapplibrary.domain.model.MovieDetail

interface MoviesRepository {
    suspend fun getPopularMovies(): List<Movie>

    suspend fun getMovieDetail(movieId: Int): MovieDetail
}