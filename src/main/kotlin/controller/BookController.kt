package ru.guap.controller

import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail
import org.koin.ktor.ext.inject
import ru.guap.dto.BookDto
import ru.guap.dto.ResieveDto
import ru.guap.service.BookService

fun Application.configureBookController() {
    val bookService by inject<BookService>()

    routing {
        route("/api/books") {
            get {
                val books: List<BookDto> = bookService.findAll()
                call.respond(books)
            }
            get("/{id}") {
                val id = call.pathParameters.getOrFail("id")
                val book: BookDto = bookService.findById(id.toInt())
                call.respond(book)
            }
            post("/reserve") {
                val reserveBookDto = call.receive<ResieveDto>()
                val isReserved = bookService.reserveBook(reserveBookDto)
                call.respond(isReserved)
            }
        }
    }
}