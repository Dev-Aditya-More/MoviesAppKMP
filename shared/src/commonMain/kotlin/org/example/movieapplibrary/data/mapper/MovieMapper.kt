package org.example.movieapplibrary.data.mapper

import org.example.movieapplibrary.data.dto.MovieDetailDto
import org.example.movieapplibrary.data.dto.MovieDto
import org.example.movieapplibrary.domain.model.Movie
import org.example.movieapplibrary.domain.model.MovieDetail

fun MovieDto.toDomain(): Movie {

    println("Mapping movie id=$id title=$title")

    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        rating = voteAverage
    )
}

fun MovieDetailDto.toUi(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        posterUrl = posterPath?.let {
            "https://image.tmdb.org/t/p/w500$it"
        },
        backdropUrl = backdropPath?.let {
            "https://image.tmdb.org/t/p/w780$it"
        },
        rating = rating,
        overview = overview,
        tagline = tagline,
        year = releaseDate.take(4),
        duration = runtime.let { "${it / 60}h ${it % 60}m" },
        genres = genres.map { it.name }
    )
}
