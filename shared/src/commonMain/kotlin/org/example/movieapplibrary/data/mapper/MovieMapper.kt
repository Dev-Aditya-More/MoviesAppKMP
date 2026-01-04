package org.example.movieapplibrary.data.mapper

import org.example.movieapplibrary.data.dto.moviedetail.MovieDetailDto
import org.example.movieapplibrary.data.dto.movielist.MovieDto
import org.example.movieapplibrary.data.dto.moviesearch.MovieSearchItemDto
import org.example.movieapplibrary.domain.model.movielist.Movie
import org.example.movieapplibrary.domain.model.moviedetails.MovieDetail
import org.example.movieapplibrary.domain.model.moviesearch.MovieSearchResult

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

fun MovieSearchItemDto.toSearchResult(): MovieSearchResult =
    MovieSearchResult(
        id = id,
        title = title,
        poster_path = poster_path.toString(),
        vote_average = vote_average,
        release_date = release_date?.take(4) ?: "—",
    )

fun MovieSearchResult.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = poster_path,
        rating = vote_average,
        year = release_date.take(4)
    )
}
