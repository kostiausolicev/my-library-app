package ru.guap.controller

import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail
import org.koin.ktor.ext.inject
import ru.guap.service.BookService

fun Application.configureBookController() {
    val bookService by inject<BookService>()

    routing {
        route("/api/books") {
            get {
                call.respondText { "books" }
            }
            get("/{id}") {
                val id = call.pathParameters.getOrFail("id")
            }
            post("/reserve") {

            }
        }
    }
}