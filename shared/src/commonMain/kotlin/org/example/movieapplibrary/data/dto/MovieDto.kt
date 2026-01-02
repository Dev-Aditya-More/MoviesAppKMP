package org.example.movieapplibrary.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    val overview: String,

    @SerialName("release_date")
    val releaseDate: String,

    val popularity: Double,

    @SerialName("vote_average")
    val voteAverage: Double,

    val adult: Boolean
)