package ru.guap.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.transactions.transaction
import ru.guap.entity.Books
import ru.guap.entity.Orders

class BookRepository {
    suspend fun findAll(): List<ResultRow> {
        return withContext(Dispatchers.IO) {
            transaction {
                Books
                    .leftJoin(Orders, { id }, { bookId }) {
                        Orders.orderStatus neq true
                    }
                    .select(
                        Books.id,
                        Books.title,
                        Books.author,
                        Books.image,
                        Books.rating,
                        Books.category,
                        Books.date,
                        Orders.orderStatus
                    )
                    .toList()
            }
        }
    }

    suspend fun findById(id: Int): ResultRow {
        return withContext(Dispatchers.IO) {
            transaction {
                Books
                    .leftJoin(Orders, { Books.id }, { bookId }) {
                        (Orders.orderStatus neq true)
                    }
                    .select(
                        Books.id,
                        Books.title,
                        Books.author,
                        Books.image,
                        Books.rating,
                        Books.category,
                        Books.date,
                        Books.description,
                        Orders.orderStatus,
                    )
                    .limit(1)
                    .where { Books.id eq id }
                    .singleOrNull() ?: throw NoSuchElementException("Book with id $id not found")
            }
        }
    }

    suspend fun reserve(id: Int, userId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            transaction {
                val book = Books
                    .leftJoin(Orders, { Books.id }, { bookId }) {
                        Orders.orderStatus neq true
                    }
                    .select(Books.id)
                    .where { Books.id eq id }
                    .singleOrNull()
                    ?: throw NoSuchElementException("Book with id $id not found")

                if (book.getOrNull(Orders.orderStatus) == true) {
                    throw IllegalStateException("Book with id $id is already reserved")
                }

                Orders.insert {
                    it[Orders.userId] = userId
                    it[Orders.bookId] = book[Books.id]
                    it[Orders.orderDate] = kotlinx.datetime.Clock.System.now()
                    it[Orders.orderStatus] = true
                }.insertedCount == 1
            }
        }
    }
}