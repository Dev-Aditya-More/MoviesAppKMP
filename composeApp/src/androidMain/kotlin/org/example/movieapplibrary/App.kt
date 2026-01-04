package org.example.movieapplibrary

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.movieapplibrary.ui.navigation.AppNavGraph
import org.example.movieapplibrary.viewmodels.MovieDetailViewModel
import org.example.movieapplibrary.viewmodels.MoviesViewModel
import org.example.movieapplibrary.viewmodels.SearchViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun App(modifier: Modifier = Modifier){
    MaterialTheme {
        AppNavGraph()
    }
}
