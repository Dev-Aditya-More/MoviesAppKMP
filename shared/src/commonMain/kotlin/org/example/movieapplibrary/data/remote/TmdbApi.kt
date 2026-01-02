package org.example.movieapplibrary.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import org.example.movieapplibrary.data.dto.MovieDetailDto
import org.example.movieapplibrary.data.dto.MoviesResponseDto

class TmdbApi(
    private val client: HttpClient
) {
    suspend fun getPopularMovies(): MoviesResponseDto {
        val response = client.get(
            "https://api.themoviedb.org/3/movie/popular"
        ) {
            parameter("language", "en-US")
            parameter("page", 1)
        }

        println("HTTP STATUS = ${response.status}")
        println("RAW BODY = ${response.bodyAsText()}")

        return response.body()
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetailDto {
        return client.get(
            "https://api.themoviedb.org/3/movie/$movieId"
        ) {
            parameter("language", "en-US")
            parameter(
                "append_to_response",
                "credits,videos"
            )
        }.body()
    }
}