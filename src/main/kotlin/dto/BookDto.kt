package ru.guap.dto

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val id: Int,
    val title: String,
    val author: String,
    val image: String? = null,
    val available: Boolean,
    val rating: Float,
    val category: String,
    val date: LocalDate,
    val description: String? = null
)
