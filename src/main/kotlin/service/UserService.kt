package ru.guap.service

import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.MissingRequestParameterException
import io.ktor.server.plugins.NotFoundException
import ru.guap.dto.ReturnDto
import ru.guap.dto.UserDto
import ru.guap.dto.UserRegisterOrUpdateDto
import ru.guap.entity.Users
import ru.guap.repository.UserRepository
import ru.guap.utils.HashUtils

class UserService(
    private val repository: UserRepository
) {
    suspend fun login(user: UserRegisterOrUpdateDto): UserDto {
        val login = user.login ?: throw MissingRequestParameterException("login")
        val password = user.password
            ?: throw MissingRequestParameterException("password")
        val user = repository.findByLoginOrNull(login)
            ?: throw NotFoundException("User with login $login not found")
        if (!HashUtils.validateHmac(password, user[Users.password])) {
            throw BadRequestException("Invalid password")
        }
        return Users.toDto(user)
    }

    suspend fun register(user: UserRegisterOrUpdateDto): UserDto {
        val fullName = user.fullName ?: throw MissingRequestParameterException("login")
        val email = user.email ?: throw MissingRequestParameterException("email")
        val phone = user.phone ?: throw MissingRequestParameterException("phone")
        val login = user.login ?: throw MissingRequestParameterException("login")
        val password = user.password
            ?.takeIf { it.isNotEmpty() }
            ?.let { HashUtils.hmacSha256(it) }
            ?: throw MissingRequestParameterException("password")

        repository.findByLoginOrNull(login)?.let {
            throw NotFoundException("User with login $login already exists")
        }

        val success = repository.save(
            fullName = fullName,
            email = email,
            phone = phone,
            login = login,
            password = password
        )
        if (success) {
            return repository.findByLoginOrNull(login)!!
                .let { Users.toDto(it) }
        } else {
            throw BadRequestException("Failed to register user")
        }
    }

    suspend fun update(user: UserRegisterOrUpdateDto): ReturnDto {
        val id = user.id
            ?: throw MissingRequestParameterException("id")
        val fullName = user.fullName
        val email = user.email
        val phone = user.phone
        val login = user.login
        val password = user.password

        val success = repository.update(
            id = id,
            fullName = fullName,
            email = email,
            phone = phone,
            login = login,
            password = password
        )
        if (success) {
            return ReturnDto(
                message = "Профиль обновлён",
                success = true
            )
        } else {
            throw BadRequestException("Failed to register user")
        }
    }
}