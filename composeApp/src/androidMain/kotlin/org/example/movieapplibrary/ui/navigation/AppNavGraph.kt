package org.example.movieapplibrary.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.example.movieapplibrary.data.repository.FavouritesStore
import org.example.movieapplibrary.ui.screens.details.MovieDetailRoute
import org.example.movieapplibrary.ui.screens.movieMain.MoviesScreen
import org.example.movieapplibrary.viewmodels.MovieDetailViewModel
import org.example.movieapplibrary.viewmodels.MoviesViewModel
import org.example.movieapplibrary.viewmodels.SearchViewModel

@OptIn(ExperimentalAnimationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavGraph() {

    SharedTransitionLayout {

        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.MOVIES
        ) {

            composable(
                route = Routes.MOVIES,
                exitTransition = {
                    fadeOut(tween(300))
                }
            ) {
                MoviesScreen(
                    animatedScope = this@composable,
                    onMovieClick = { movieId ->
                        navController.navigate("${Routes.DETAILS}/$movieId")
                    }
                )
            }

            composable(
                route = "${Routes.DETAILS}/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
                enterTransition = {
                    fadeIn(tween(300))
                }
            ) {
                MovieDetailRoute(
                    movieId = it.arguments!!.getInt("movieId"),
                    animatedVisibilityScope = this@composable,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}