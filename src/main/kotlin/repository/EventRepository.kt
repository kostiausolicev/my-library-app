package ru.guap.repository

import io.ktor.server.plugins.NotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.transactions.transaction
import ru.guap.entity.Events
import ru.guap.entity.Filials

class EventRepository {
    suspend fun findAll(): List<ResultRow> {
        return withContext(Dispatchers.IO) {
            transaction {
                Events
                    .leftJoin(Filials, { Events.filialId }, { Filials.id })
                    .select(
                        Events.id,
                        Events.title,
                        Events.startAt,
                        Events.image,
                        Filials.address
                    )
                    .toList()
            }
        }
    }

    suspend fun findById(id: Int): ResultRow {
        return withContext(Dispatchers.IO) {
            transaction {
                Events
                    .leftJoin(Filials, { Events.filialId }, { Filials.id })
                    .select(
                        Events.id,
                        Events.title,
                        Events.startAt,
                        Events.image,
                        Events.description,
                        Filials.address
                    )
                    .where { Events.id eq id }
                    .singleOrNull() ?: throw NotFoundException("Event with id $id not found")
            }
        }
    }
}