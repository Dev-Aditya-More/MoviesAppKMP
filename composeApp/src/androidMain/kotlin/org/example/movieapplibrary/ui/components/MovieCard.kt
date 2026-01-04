package org.example.movieapplibrary.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.example.movieapplibrary.domain.model.movielist.Movie

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieCard(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    posterUrl: String? = null,
    rating: Double = 0.0,
    isFavourite: Boolean = false,
    onFavouriteClick: () -> Unit = {},
    onClick: () -> Unit = {},
    movie: Movie = Movie(
        id = 0,
        title = "Movie Title",
        posterPath = null,
        rating = 0.0,
        year = null
    )
) {
    Box(
        modifier = modifier
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
    ) {

        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .sharedElement(
                    sharedContentState = rememberSharedContentState(
                        key = "poster-${movie.id}"
                    ),
                    animatedVisibilityScope = animatedVisibilityScope
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.55f)
                        ),
                        startY = 120f
                    )
                )
        )

        RatingBadge(
            rating = rating,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        )

        FavouriteButton(
            isFavourite = isFavourite,
            onClick = onFavouriteClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )
    }
}
