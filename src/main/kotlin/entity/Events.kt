package ru.guap.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object Events : IntIdTable("events") {
    val title = text("title")
    val description = text("description").nullable()
    val startAt = timestamp("startAt")
    val image = text("image").nullable()
    val filialId = reference("filialId", Filials)
}