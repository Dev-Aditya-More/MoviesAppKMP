package org.example.movieapplibrary.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    val id: Int,
    val title: String,
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("release_date")
    val releaseDate: String,

    val runtime: Int,

    @SerialName("vote_average")
    val rating: Double,

    val genres: List<GenreDto> = emptyList(),

    val tagline: String? = null,

    // optional but future-proof
    val popularity: Double? = null
)
