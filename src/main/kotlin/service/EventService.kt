package ru.guap.service

import ru.guap.dto.MeetingDto
import ru.guap.entity.Events
import ru.guap.repository.EventRepository

class EventService(private val repository: EventRepository) {

    suspend fun findAll(): List<MeetingDto> {
        val results = repository.findAll()
        return results.map { Events.toDto(it) }
    }

    suspend fun findById(id: Int): MeetingDto {
        val event = repository.findById(id)
        return Events.toDto(event)
    }

    suspend fun findAllByFilialId(filialId: Int): List<MeetingDto> {
        val results = repository.findAllByFilialId(filialId)
        return results.map { Events.toDto(it) }
    }
}