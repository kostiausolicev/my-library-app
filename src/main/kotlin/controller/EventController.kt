package ru.guap.controller

import io.ktor.server.application.Application
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail
import org.koin.ktor.ext.inject
import ru.guap.service.EventService

fun Application.configureEventController() {
    val eventService by inject<EventService>()
    
    routing {
        route("/api/meetings") {
            get {

            }
            get("/{id}") {
                val id = call.pathParameters.getOrFail("id")

            }
        }
    }
}