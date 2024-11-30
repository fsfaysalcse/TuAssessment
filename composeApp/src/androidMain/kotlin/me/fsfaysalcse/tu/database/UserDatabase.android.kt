package me.fsfaysalcse.tu.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import me.fsfaysalcse.tu.database.UserDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<UserDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("user.db")
    return Room.databaseBuilder<UserDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}