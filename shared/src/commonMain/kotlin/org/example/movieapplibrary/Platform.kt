package org.example.movieapplibrary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform