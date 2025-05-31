package ru.guap.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Int,
    val fullName: String,
    val email: String,
    val phone: String,
    val login: String,
)
