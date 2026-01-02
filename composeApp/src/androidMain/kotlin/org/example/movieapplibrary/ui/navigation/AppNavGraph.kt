package org.example.movieapplibrary.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.movieapplibrary.ui.screens.details.MovieDetailRoute
import org.example.movieapplibrary.ui.screens.movieMain.MoviesScreen
import org.example.movieapplibrary.viewmodels.MovieDetailViewModel
import org.example.movieapplibrary.viewmodels.MoviesViewModel

@Composable
fun AppNavGraph(
    moviesViewModel: MoviesViewModel,
    detailViewModel: MovieDetailViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.MOVIES
    ) {

        composable(Routes.MOVIES) {
            MoviesScreen(
                viewModel = moviesViewModel,
                onMovieClick = { movieId ->
                    navController.navigate("${Routes.DETAILS}/$movieId")
                }
            )
        }

        composable(
            route = "${Routes.DETAILS}/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val movieId = backStackEntry.arguments!!.getInt("movieId")

            MovieDetailRoute(
                movieId = movieId,
                viewModel = detailViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}