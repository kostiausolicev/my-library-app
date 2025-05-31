package ru.guap.controller

import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject
import ru.guap.dto.UserRegisterOrUpdateDto
import ru.guap.service.UserService

fun Application.configureUserController() {
    val userService: UserService by inject<UserService>()

    routing {
        route("/api/auth") {
            post("/login") {
                val login = call.receive<UserRegisterOrUpdateDto>()
                val result = userService.login(login)
                call.respond(result)
            }
            post("/register") {
                val register = call.receive<UserRegisterOrUpdateDto>()
                val result = userService.register(register)
                call.respond(result)
            }
        }
        route("/api/users") {
            patch("/update") {
                val user = call.receive<UserRegisterOrUpdateDto>()
                val result = userService.update(user)
                call.respond(result)
            }
        }
    }
}