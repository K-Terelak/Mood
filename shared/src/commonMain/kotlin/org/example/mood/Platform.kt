package org.example.mood

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform