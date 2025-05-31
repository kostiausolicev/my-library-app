package ru.guap.config

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import ru.guap.controller.configureBookController
import ru.guap.controller.configureEventController
import ru.guap.controller.configureFilialController
import ru.guap.controller.configureUserController

fun Application.configureHTTP() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader("MyCustomHeader")
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    routing {
        configureUserController()
        configureBookController()
        configureFilialController()
        configureEventController()
    }
}
