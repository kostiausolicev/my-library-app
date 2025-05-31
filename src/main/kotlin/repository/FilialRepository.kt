package ru.guap.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.transactions.transaction
import ru.guap.entity.Filials

class FilialRepository {

    suspend fun findAll(): List<ResultRow> {
        return withContext(Dispatchers.IO) {
            transaction {
                Filials
                    .select(
                        Filials.id,
                        Filials.name,
                        Filials.address,
                        Filials.openAt,
                        Filials.closeAt,
                        Filials.image
                    )
                    .toList()
            }
        }
    }

    suspend fun findById(id: Int): ResultRow? {
        return withContext(Dispatchers.IO) {
            transaction {
                Filials
                    .select(
                        Filials.id,
                        Filials.name,
                        Filials.address,
                        Filials.openAt,
                        Filials.closeAt,
                        Filials.image
                    )
                    .where { Filials.id eq id }
                    .singleOrNull()
            }
        }
    }
}