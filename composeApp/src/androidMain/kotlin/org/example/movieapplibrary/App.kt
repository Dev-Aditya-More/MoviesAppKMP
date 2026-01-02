package org.example.movieapplibrary

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.movieapplibrary.ui.navigation.AppNavGraph
import org.example.movieapplibrary.viewmodels.MovieDetailViewModel
import org.example.movieapplibrary.viewmodels.MoviesViewModel

@Composable
fun App(
    viewModel: MoviesViewModel,
    detailViewModel: MovieDetailViewModel
) {
    MaterialTheme {
        AppNavGraph(
            moviesViewModel = viewModel,
            detailViewModel = detailViewModel
        )
    }
}
