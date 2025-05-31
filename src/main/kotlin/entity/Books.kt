package ru.guap.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.kotlin.datetime.date
import ru.guap.dto.BookDto

object Books : IntIdTable("books") {
    val title = text("title")
    val author = text("author")
    val image = text("image").nullable()
    val rating = float("rating")
    val category = text("category")
    val date = date("date")
    val description = text("description").nullable()

    fun toDto(row: ResultRow): BookDto {
        return BookDto(
            id = row[id].value,
            title = row[title],
            author = row[author],
            image = row[image],
            available = row.getOrNull(Orders.orderStatus) ?: true,
            rating = row[rating],
            category = row[category],
            date = row[date],
            description = row.getOrNull(description)
        )
    }
}