package org.example.movieapplibrary.ui.screens.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.movieapplibrary.data.repository.FavouritesStore
import org.example.movieapplibrary.ui.components.MovieCard
import org.example.movieapplibrary.ui.components.MoviesTopBar
import org.example.movieapplibrary.ui.navigation.Tab
import org.example.movieapplibrary.viewmodels.MoviesViewModel

@Composable
fun FavouritesScreen(
    viewModel: MoviesViewModel,
    onMovieClick: (Int) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val favouriteMovies = remember(state.movies, state.favourites) {
        state.movies.filter { it.id in state.favourites }
    }

    var currentTab by rememberSaveable {
        mutableStateOf(Tab.FAVOURITES)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF1B0F2F), Color(0xFF2E0B4F))
                )
            )
    ) {
        MoviesTopBar(
            currentTab = currentTab,
            onToggleFavourites = {
                currentTab =
                    if (currentTab == Tab.FAVOURITES)
                        Tab.MOVIES
                    else
                        Tab.FAVOURITES
            }
        )

        if (favouriteMovies.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No favorite movies yet",
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favouriteMovies) { movie ->
                    MovieCard(
                        posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                        rating = movie.rating,
                        isFavourite = true,
                        onFavouriteClick = {
                            viewModel.toggleFavourite(movie.id)
                        },
                        onClick = { onMovieClick(movie.id) }
                    )
                }
            }
        }
    }
}
