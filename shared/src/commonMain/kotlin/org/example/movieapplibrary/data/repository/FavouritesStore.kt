package org.example.movieapplibrary.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FavouritesStore {

    private val _favourites = MutableStateFlow<Set<Int>>(emptySet())
    val favourites: StateFlow<Set<Int>> = _favourites

    fun isFavourite(movieId: Int): Boolean {
        return _favourites.value.contains(movieId)
    }

    fun toggle(movieId: Int) {
        _favourites.update { current ->
            if (current.contains(movieId)) {
                current - movieId
            } else {
                current + movieId
            }
        }
    }
}
