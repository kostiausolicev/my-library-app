package ru.guap.config

import io.ktor.http.HttpHeaders
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.routing.routing
import ru.guap.controller.configureBookController
import ru.guap.controller.configureEventController
import ru.guap.controller.configureFilialController
import ru.guap.controller.configureUserController

fun Application.configureHTTP() {
    install(CORS) {
        anyMethod()
        anyHost()
        allowCredentials = true
        allowNonSimpleContentTypes = true
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
    }

    routing {
        configureUserController()
        configureBookController()
        configureFilialController()
        configureEventController()
    }
}
