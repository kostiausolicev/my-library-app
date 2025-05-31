package ru.guap.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReturnDto(
    val success: Boolean,
    val message: String
)
