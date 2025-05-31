package ru.guap.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object Orders : IntIdTable("orders") {
    val userId = reference("userid", Users)
    val bookId = reference("bookid", Books)
    val orderDate = timestamp("orderdate")
    // Возвращена ли книга
    val orderStatus = bool("orderstatus")
}