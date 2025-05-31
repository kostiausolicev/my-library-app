package ru.guap.config

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.MissingRequestParameterException
import io.ktor.server.plugins.NotFoundException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText

fun Application.configureRouting() {
    install(StatusPages) {
        exception<NotFoundException> { call, cause ->
            call.respondText(text = "404: ${cause.message ?: "Not Found"}", status = HttpStatusCode.NotFound)
        }
        exception<BadRequestException> { call, cause ->
            call.respondText(text = "400: ${cause.message ?: "Bad Request"}", status = HttpStatusCode.BadRequest)
        }
        exception<MissingRequestParameterException> { call, cause ->
            call.respondText(text = "400: ${cause.message ?: "Bad Request"}", status = HttpStatusCode.BadRequest)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
}
