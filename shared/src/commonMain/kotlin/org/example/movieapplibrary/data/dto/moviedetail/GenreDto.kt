package org.example.movieapplibrary.data.dto.moviedetail

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)
