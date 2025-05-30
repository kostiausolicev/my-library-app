package ru.guap

import io.ktor.server.application.Application
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    val config = environment.config.config("postgres")

    val url = config.property("url").getString()
    val driver = config.property("driver").getString()
    val user = config.property("user").getString()
    val password = config.property("password").getString()

    Database.connect(url = url, driver = driver, user = user, password = password)

    val flyway = Flyway.configure()
        .dataSource(
            url,
            user,
            password
        )
        .schemas("public")
        .baselineOnMigrate(true)
        .locations("classpath:db/migration")
        .load()
    flyway.migrate()
}
