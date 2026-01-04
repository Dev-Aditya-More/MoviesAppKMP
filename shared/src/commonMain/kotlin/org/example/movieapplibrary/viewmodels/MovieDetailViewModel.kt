package org.example.movieapplibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.movieapplibrary.data.repository.FavouritesStore
import org.example.movieapplibrary.data.repository.MoviesRepository
import org.example.movieapplibrary.domain.utils.MovieDetailUiState

class MovieDetailViewModel(
    private val repository: MoviesRepository,
    private val favouritesStore: FavouritesStore
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailUiState())
    val state: StateFlow<MovieDetailUiState> = _state

    fun load(movieId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                val movie = repository.getMovieDetail(movieId)
                _state.update {
                    it.copy(
                        isLoading = false,
                        movie = movie
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

    fun toggleFavourite() {
        val movieId = _state.value.movie?.id ?: return
        favouritesStore.toggle(movieId)
    }
}
