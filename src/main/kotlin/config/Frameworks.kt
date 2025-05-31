package ru.guap.config

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import ru.guap.repository.BookRepository
import ru.guap.repository.EventRepository
import ru.guap.repository.FilialRepository
import ru.guap.repository.UserRepository
import ru.guap.service.BookService
import ru.guap.service.EventService
import ru.guap.service.FilialService
import ru.guap.service.UserService

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            single { UserService(UserRepository()) }
            single { BookService(BookRepository()) }
            single { EventService(EventRepository()) }
            single { FilialService(FilialRepository(), get()) }
        })
    }
}
