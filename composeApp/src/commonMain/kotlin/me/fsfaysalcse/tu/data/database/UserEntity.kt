package me.fsfaysalcse.tu.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.fsfaysalcse.tu.data.models.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val email: String,
    val password: String
) {
    fun toUser() = User(
        name = name,
        phone = phone,
        email = email,
        password = password
    )
}