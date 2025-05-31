package ru.guap.entity

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import ru.guap.dto.MeetingDto

object Events : IntIdTable("events") {
    val title = text("title")
    val description = text("description").nullable()
    val startAt = timestamp("startat")
    val image = text("image").nullable()
    val filialId = reference("filialid", Filials)

    fun toDto(row: ResultRow): MeetingDto {
        val datetimeStr = row[startAt].toString()
        val instant = Instant.parse(datetimeStr)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        val date = localDateTime.date
        val time = localDateTime.time
        return MeetingDto(
            id = row[id].value,
            title = row[title],
            date = date,
            time = "${time.hour}:${time.minute}",
            image = row[image],
            location = row[Filials.address]
        )
    }
}