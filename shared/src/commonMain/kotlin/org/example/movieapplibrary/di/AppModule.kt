package org.example.movieapplibrary.di

import org.example.movieapplibrary.data.remote.TmdbApi
import org.example.movieapplibrary.data.remote.createHttpClient
import org.example.movieapplibrary.data.repository.FavouritesStore
import org.example.movieapplibrary.data.repository.MovieRepoImpl
import org.example.movieapplibrary.data.repository.MoviesRepository
import org.example.movieapplibrary.viewmodels.MovieDetailViewModel
import org.example.movieapplibrary.viewmodels.MoviesViewModel
import org.example.movieapplibrary.viewmodels.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun appModule(apiToken: String) = module {

    // Network
    single {
        createHttpClient(
            token = apiToken
        )
    }

    single {
        TmdbApi(get())
    }

    // Repository
    single<MoviesRepository> {
        MovieRepoImpl(get())
    }

    // Store (shared across VMs)
    single {
        FavouritesStore()
    }

    // ViewModels
    viewModel {
        MoviesViewModel(
            repository = get(),
            favouritesStore = get()
        )
    }

    viewModel {
        MovieDetailViewModel(
            repository = get(),
            favouritesStore = get()
        )
    }

    viewModel {
        SearchViewModel(
            repository = get()
        )
    }
}