package me.fsfaysalcse.tu.di

import me.fsfaysalcse.tu.data.database.getRoomDatabase
import me.fsfaysalcse.tu.ui.viewModels.UserViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect val targetModule: Module

val sharedModule = module {
    single { getRoomDatabase(get()) }
    single { getRoomDatabase(get()) }
    viewModelOf(::UserViewModel)
}

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(targetModule, sharedModule)
    }
}