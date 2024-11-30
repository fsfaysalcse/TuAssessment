package me.fsfaysalcse.tu

import androidx.compose.ui.window.ComposeUIViewController
import me.fsfaysalcse.tu.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }