package me.fsfaysalcse.tu.di

import me.fsfaysalcse.tu.database.getDatabaseBuilder
import org.koin.dsl.module

actual val targetModule = module {
    single { getDatabaseBuilder() }
}