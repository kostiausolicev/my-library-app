package ru.guap.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object Books : IntIdTable("books") {
    val title = text("title")
    val author = text("author")
    val image = text("image").nullable()
    val available = bool("available").default(true)
    val rating = float("rating")
    val category = text("category")
    val date = date("date")
    val description = text("description").nullable()
}