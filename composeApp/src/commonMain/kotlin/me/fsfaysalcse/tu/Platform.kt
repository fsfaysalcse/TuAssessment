package me.fsfaysalcse.tu

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform