package ru.guap.entity

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import ru.guap.dto.FilialDto
import ru.guap.dto.MeetingDto
import kotlin.time.ExperimentalTime

object Filials : IntIdTable("filials") {
    val name = text("name")
    val address = text("address")
    val openAt = integer("openat")
    val closeAt = integer("closeat")
    val image = text("image").nullable()

    @OptIn(ExperimentalTime::class)
    fun toDto(row: ResultRow, events: List<MeetingDto> = emptyList()): FilialDto {
        val open = row[openAt]
        val close = row[closeAt]

        val currentHours = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time.hour
        val isOpen = currentHours in open..close
        return FilialDto(
            id = row[id].value,
            name = row[name],
            address = row[address],
            hours = "$open:00 - $close:00",
            open = isOpen,
            image = row[image],
            events = events
        )
    }
}