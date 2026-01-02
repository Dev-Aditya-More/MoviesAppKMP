package org.example.movieapplibrary.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.movieapplibrary.viewmodels.MovieDetailViewModel

@Composable
fun MovieDetailRoute(
    movieId: Int,
    viewModel: MovieDetailViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(movieId) {
        viewModel.load(movieId)
    }

    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.movie != null -> {
            MovieDetailScreen(
                movie = state.movie!!,
                isFavourite = state.isFavourite,
                onBack = onBack,
                onToggleFavourite = viewModel::toggleFavourite
            )
        }

        state.error != null -> {
            Text("Error: ${state.error}")
        }
    }
}
