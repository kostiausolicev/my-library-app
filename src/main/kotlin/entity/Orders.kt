package ru.guap.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object Orders : IntIdTable("orders") {
    val userId = reference("userId", Users)
    val bookId = reference("bookId", Books)
    val orderDate = timestamp("orderDate")
}