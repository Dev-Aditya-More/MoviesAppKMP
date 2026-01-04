package org.example.movieapplibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.movieapplibrary.data.repository.MoviesRepository
import org.example.movieapplibrary.domain.utils.SearchUiState

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState> = _state

    fun onQueryChange(query: String) {
        _state.update { it.copy(query = query) }
    }

    init {
        viewModelScope.launch {
            _state
                .map { it.query }
                .debounce(400)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isBlank()) {
                        _state.update { it.copy(results = emptyList()) }
                    } else {
                        search(query)
                    }
                }
        }
    }

    private suspend fun search(query: String) {
        _state.update { it.copy(isLoading = true) }
        runCatching { repository.searchMovies(query) }
            .onSuccess {
                _state.update { s ->
                    s.copy(results = it, isLoading = false)
                }
            }
            .onFailure {
                _state.update { s ->
                    s.copy(error = it.message, isLoading = false)
                }
            }
    }
}

