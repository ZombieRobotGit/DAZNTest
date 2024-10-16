package com.madbox.dazntest.domain.events

import com.madbox.dazntest.data.model.EventDTO
import com.madbox.dazntest.data.repository.EventsRepositoryInterface
import javax.inject.Inject

class FetchEventsUseCase @Inject constructor(private val repository: EventsRepositoryInterface) {

    suspend fun execute(): List<EventDTO> {
        val fetchedData = repository.getEvents()
        return fetchedData.data ?: emptyList()
    }

}