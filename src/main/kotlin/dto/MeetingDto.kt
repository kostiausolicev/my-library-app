package ru.guap.dto

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class MeetingDto(
    val id: Int,
    val title: String,
    val date: LocalDate,
    val time: String,
    val location: String,
    val image: String? = null,
)
