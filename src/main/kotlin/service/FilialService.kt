package ru.guap.service

import io.ktor.server.plugins.NotFoundException
import ru.guap.dto.FilialDto
import ru.guap.entity.Filials
import ru.guap.repository.FilialRepository

class FilialService(
    private val repository: FilialRepository,
    private val eventService: EventService
) {
    suspend fun findAll(): List<FilialDto> {
        return repository.findAll().map {
            Filials.toDto(it)
        }
    }

    suspend fun findById(id: Int): FilialDto {
        val filial = repository.findById(id)
            ?.let {
                Filials.toDto(
                    it,
                    eventService.findAllByFilialId(id)
                )
            }
            ?: throw NotFoundException("Filial with id $id not found")
        return filial
    }
}