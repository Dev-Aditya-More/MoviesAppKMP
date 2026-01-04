package org.example.movieapplibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.example.movieapplibrary.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(
                appModule(
                    apiToken = BuildConfig.TMDB_Token
                )
            )
        }

        setContent {

            App()
        }
    }
}