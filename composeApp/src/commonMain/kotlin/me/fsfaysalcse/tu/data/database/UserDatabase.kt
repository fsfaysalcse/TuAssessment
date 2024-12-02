package me.fsfaysalcse.tu.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(
    entities = [UserEntity::class],
    version = 2
)
@ConstructedBy(UserDatabaseConstructor::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object UserDatabaseConstructor : RoomDatabaseConstructor<UserDatabase> {
    override fun initialize(): UserDatabase
}