package ru.guap.controller

import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail
import org.koin.ktor.ext.inject
import ru.guap.service.FilialService

fun Application.configureFilialController() {
    val filialService by inject<FilialService>()

    routing {
        route("/api/filials") {
            get {
                val filials = filialService.findAll()
                call.respond(filials)
            }
            get("/{id}") {
                val id = call.pathParameters.getOrFail("id").toInt()
                val filial = filialService.findById(id)
                call.respond(filial)
            }
        }
    }
}
