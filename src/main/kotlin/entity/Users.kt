package ru.guap.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import ru.guap.dto.UserDto

object Users : IntIdTable("users") {
    val fullName = varchar("fullname", 50)
    val email = varchar("email", 50)
    val phone = varchar("phone", 20)
    val login = text("login")
    val password = text("password")

    fun toDto(row: ResultRow): UserDto {
        return UserDto(
            id = row[id].value,
            fullName = row[fullName],
            email = row[email],
            phone = row[phone],
            login = row[login],
        )
    }
}