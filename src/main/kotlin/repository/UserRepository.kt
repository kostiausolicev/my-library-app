package ru.guap.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import ru.guap.dto.UserRegisterOrUpdateDto
import ru.guap.entity.Users

class UserRepository {
    suspend fun findByLoginOrNull(login: String): ResultRow? {
        return withContext(Dispatchers.IO) {
            transaction {
                Users.selectAll()
                    .where { Users.login eq login }
                    .singleOrNull()
            }
        }
    }

    /**
     * Обновляет данные пользователя по логину.
     *
     * @return True, если добавление прошло успешно, иначе False.
     */
    suspend fun save(
        fullName: String,
        email: String,
        phone: String,
        login: String,
        password: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            transaction {
                Users.insert {
                    it[Users.fullName] = fullName
                    it[Users.email] = email
                    it[Users.phone] = phone
                    it[Users.login] = login
                    it[Users.password] = password
                }.insertedCount == 1
            }
        }
    }

    /**
     * Обновляет данные пользователя
     *
     * @return True, если обновление прошло успешно, иначе False.
     */
    suspend fun update(
        id: Int,
        login: String? = null,
        fullName: String? = null,
        email: String? = null,
        phone: String? = null,
        password: String? = null
    ): Boolean {
        return withContext(Dispatchers.IO) {
            transaction {
                Users.update({ Users.id eq id }) { user ->
                    login?.let { user[Users.login] = it }
                    fullName?.let { user[Users.fullName] = it }
                    email?.let { user[Users.email] = it }
                    phone?.let { user[Users.phone] = it }
                    password?.let { user[Users.password] = it }
                } == 1
            }
        }
    }
}