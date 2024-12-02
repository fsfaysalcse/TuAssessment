package me.fsfaysalcse.tu

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import me.fsfaysalcse.tu.database.createDataStore
import me.fsfaysalcse.tu.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) {
    val prefs = remember { createDataStore() }
    App(prefs)
}