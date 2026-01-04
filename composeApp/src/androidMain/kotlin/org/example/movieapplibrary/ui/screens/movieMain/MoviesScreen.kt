package org.example.movieapplibrary.ui.screens.movieMain

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import org.example.movieapplibrary.data.mapper.toMovie
import org.example.movieapplibrary.domain.model.movielist.Movie
import org.example.movieapplibrary.ui.components.MovieCard
import org.example.movieapplibrary.ui.components.MoviesTopBar
import org.example.movieapplibrary.ui.navigation.Tab
import org.example.movieapplibrary.viewmodels.MoviesViewModel
import org.example.movieapplibrary.viewmodels.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MoviesScreen(
    animatedScope: AnimatedContentScope,
    viewModel: MoviesViewModel = koinViewModel(),
    searchViewModel : SearchViewModel = koinViewModel(),
    onMovieClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val searchState by searchViewModel.state.collectAsStateWithLifecycle()

    var searchQuery by rememberSaveable { mutableStateOf("") }

    var currentTab by rememberSaveable {
        mutableStateOf(Tab.MOVIES)
    }
    val moviesToShow: List<Movie> = when {
        searchQuery.isNotBlank() ->
            searchState.results.map { it.toMovie() }

        currentTab == Tab.FAVOURITES ->
            state.movies.filter { it.id in state.favourites }

        else -> state.movies
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF1B0F2F),
                        Color(0xFF2E0B4F)
                    )
                )
            ).windowInsetsPadding(
                WindowInsets.systemBars
            )
    ) {

        MoviesTopBar(
            currentTab = currentTab,
            searchQuery = searchQuery,
            onSearchQueryChange = {
                searchQuery = it
                searchViewModel.onQueryChange(it)
            },
            onToggleFavourites = {
                currentTab =
                    if (currentTab == Tab.MOVIES)
                        Tab.FAVOURITES
                    else
                        Tab.MOVIES
            }
        )

        Spacer(Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(moviesToShow) { movie ->

                val posterUrl = movie.posterPath?.let {
                    "https://image.tmdb.org/t/p/w500$it"
                }
                Log.d("POSTER_URL", posterUrl ?: "NULL")
                MovieCard(
                    animatedVisibilityScope = animatedScope,
                    posterUrl = posterUrl,
                    rating = movie.rating,
                    isFavourite = state.favourites.contains(movie.id),
                    onFavouriteClick = {
                        viewModel.toggleFavourite(movie.id)
                    },
                    onClick = {
                        onMovieClick(movie.id)
                    },
                    movie = movie,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
