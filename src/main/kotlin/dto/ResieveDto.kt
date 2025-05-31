package ru.guap.dto

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class ResieveDto(
    val bookId: Int,
    val userId: Int,
    val pickupDate: LocalDate
)
