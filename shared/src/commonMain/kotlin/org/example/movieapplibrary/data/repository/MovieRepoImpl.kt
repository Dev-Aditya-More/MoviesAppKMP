package org.example.movieapplibrary.data.repository

import org.example.movieapplibrary.data.mapper.toDomain
import org.example.movieapplibrary.data.mapper.toUi
import org.example.movieapplibrary.data.remote.TmdbApi
import org.example.movieapplibrary.domain.model.Movie
import org.example.movieapplibrary.domain.model.MovieDetail

class MovieRepoImpl(
    private val api: TmdbApi
) : MoviesRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        val response = api.getPopularMovies()

        println("DTO page = ${response.page}")
        println("DTO results size = ${response.results.size}")

        response.results.take(1).forEach {
            println("First DTO movie title = ${it.title}")
        }

        return response.results.map { it.toDomain() }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return api.getMovieDetails(movieId).toUi()
    }
}