package org.example.movieapplibrary.data.repository

class FavouritesStore {

    private val favourites = mutableSetOf<Int>()

    fun isFavourite(movieId: Int): Boolean {
        return favourites.contains(movieId)
    }

    fun toggle(movieId: Int) {
        if (!favourites.add(movieId)) {
            favourites.remove(movieId)
        }
    }

    fun getAll(): Set<Int> = favourites.toSet()
}
