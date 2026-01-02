package org.example.movieapplibrary.viewmodels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.movieapplibrary.data.repository.FavouritesStore
import org.example.movieapplibrary.data.repository.MoviesRepository
import org.example.movieapplibrary.domain.utils.MoviesUiState

class MoviesViewModel(
    private val repository: MoviesRepository,
    private val favouritesStore: FavouritesStore
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _state = MutableStateFlow(MoviesUiState())
    val state: StateFlow<MoviesUiState> = _state

    fun loadMovies() {
        scope.launch {
            try {
                val movies = repository.getPopularMovies()

                println("VM received movies = ${movies.size}")

                _state.update {
                    it.copy(
                        movies = movies,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                println("VM ERROR = ${e.message}")
            }
        }
    }

    fun toggleFavourite(movieId: Int) {

        favouritesStore.toggle(movieId)

        _state.update {
            it.copy(
                favourites = favouritesStore.getAll()
            )
        }
    }
}