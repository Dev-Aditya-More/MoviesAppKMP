package org.example.movieapplibrary

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.example.movieapplibrary.data.remote.TmdbApi
import org.example.movieapplibrary.data.remote.createHttpClient
import org.example.movieapplibrary.data.repository.FavouritesStore
import org.example.movieapplibrary.data.repository.MovieRepoImpl
import org.example.movieapplibrary.viewmodels.MovieDetailViewModel
import org.example.movieapplibrary.viewmodels.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        Log.d("API_KEY", "Key length = ${BuildConfig.TMDB_API_KEY}")

        val api = TmdbApi(
            client = createHttpClient(
                BuildConfig.TMDB_API_KEY
            ),
        )

        val repository = MovieRepoImpl(api)
        val favouritesStore = FavouritesStore()
        val moviesViewModel = MoviesViewModel(repository, favouritesStore)
        val detailViewModel = MovieDetailViewModel(repository, favouritesStore)

        setContent {

            App(
                viewModel = moviesViewModel,
                detailViewModel = detailViewModel
            )
        }
    }
}