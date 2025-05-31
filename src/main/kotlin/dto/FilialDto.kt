package ru.guap.dto

import kotlinx.serialization.Serializable

@Serializable
data class FilialDto(
    val id: Int,
    val name: String,
    val address: String,
    val hours: String,
    val open: String,
    val image: String? = null,
    val description: String? = null,
    val events: List<MeetingDto>? = null
)
