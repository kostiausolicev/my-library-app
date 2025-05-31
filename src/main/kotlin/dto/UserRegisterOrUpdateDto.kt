package ru.guap.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterOrUpdateDto(
    val id: Int? = null,
    val fullName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val login: String? = null,
    val password: String? = null,
)
