package org.example.movieapplibrary.ui.screens.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.movieapplibrary.data.repository.FavouritesStore
import org.example.movieapplibrary.viewmodels.MovieDetailViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieDetailRoute(
    movieId: Int,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: MovieDetailViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val favouritesStore: FavouritesStore = koinInject()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val favourites by favouritesStore.favourites.collectAsStateWithLifecycle()

    LaunchedEffect(movieId) {
        viewModel.load(movieId)
    }

    val isFavourite = favourites.contains(movieId)
    Box(modifier = Modifier.fillMaxSize()) {

        MovieDetailScreen(
            animatedVisibilityScope = animatedVisibilityScope,
            movie = state.movie,
            isFavourite = isFavourite,
            onBack = onBack,
            onToggleFavourite = viewModel::toggleFavourite
        )

        state.error?.let {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(it, color = Color.Red)
            }
        }
    }
}


