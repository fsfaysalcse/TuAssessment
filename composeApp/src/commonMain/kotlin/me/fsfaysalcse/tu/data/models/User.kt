package me.fsfaysalcse.tu.data.models

import me.fsfaysalcse.tu.data.database.UserEntity

data class User(
    val name: String,
    val phone: String,
    val email: String,
    val password: String
) {
    fun toUserEntity() = UserEntity(
        name = name,
        phone = phone,
        email = email,
        password = password
    )
}