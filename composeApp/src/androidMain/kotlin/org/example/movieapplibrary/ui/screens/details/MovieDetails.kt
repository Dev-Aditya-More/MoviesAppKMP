package org.example.movieapplibrary.ui.screens.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.movieapplibrary.domain.model.moviedetails.MovieDetail
import org.example.movieapplibrary.ui.components.InfoBlock
import org.example.movieapplibrary.ui.components.InfoCard
import org.example.movieapplibrary.ui.components.PosterSection
import org.example.movieapplibrary.ui.components.SynopsisCard

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieDetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    movie: MovieDetail?,
    isFavourite: Boolean,
    onBack: () -> Unit,
    onToggleFavourite: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0F0822),
                        Color(0xFF2E0B4F)
                    )
                )
            ).windowInsetsPadding(WindowInsets.statusBars)
            .sharedElement(
                sharedContentState = rememberSharedContentState(
                    key = "poster-${movie?.id}"
                ),
                animatedVisibilityScope = animatedVisibilityScope
            )
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {

            item {
                PosterSection(
                    posterUrl = movie?.backdropUrl.toString(),
                    rating = movie?.rating ?: 0.0,
                    isFavourite = isFavourite,
                    onBack = onBack,
                    onFavouriteClick = onToggleFavourite
                )
            }

            item {
                if (movie != null) {
                    InfoCard(movie)
                }
            }

            item { SynopsisCard(movie?.overview ?: "") }

            item {
                InfoBlock(
                    title = "Genres",
                    content = movie?.genres?.joinToString(", ") ?: ""
                )
            }

            item {
                InfoBlock(
                    title = "Runtime",
                    content = movie?.duration ?: ""
                )
            }
        }
    }
}


