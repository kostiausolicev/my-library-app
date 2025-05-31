package ru.guap.service

import ru.guap.dto.BookDto
import ru.guap.dto.ResieveDto
import ru.guap.dto.ReturnDto
import ru.guap.entity.Books
import ru.guap.repository.BookRepository

class BookService(private val repository: BookRepository) {
    suspend fun findAll(): List<BookDto> {
        return repository.findAll()
            .map { Books.toDto(it) }
    }

    suspend fun findById(id: Int): BookDto {
        return repository.findById(id)
            .let { Books.toDto(it) }
    }

    suspend fun reserveBook(reserveBookDto: ResieveDto): ReturnDto {
        val bookId = reserveBookDto.bookId
        val userId = reserveBookDto.userId
        val result = repository.reserve(bookId, userId)
        return ReturnDto(
            success = result,
            message = if (result) "Book reserved successfully" else "Failed to reserve book"
        )
    }
}