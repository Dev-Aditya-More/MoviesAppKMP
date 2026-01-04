package org.example.movieapplibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlin.collections.emptyList

class MoviesViewModel(
    private val repository: MoviesRepository,
    private val favouritesStore: FavouritesStore
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesUiState())
    val state: StateFlow<MoviesUiState> = _state

    init {
        viewModelScope.launch {
            favouritesStore.favourites.collect { favs ->
                _state.update { it.copy(favourites = favs) }
            }
        }

        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                val movies = repository.getPopularMovies()
                _state.update {
                    it.copy(
                        movies = movies,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun toggleFavourite(movieId: Int) {
        favouritesStore.toggle(movieId)
    }
}
