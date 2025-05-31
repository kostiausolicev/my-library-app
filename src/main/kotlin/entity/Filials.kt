package ru.guap.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object Filials : IntIdTable("filials") {
    val name = text("name").nullable()
    val address = text("address")
    val openAt = integer("openAt").nullable()
    val closeAt = integer("closeAt").nullable()
    val image = text("image").nullable()
}