package com.madbox.dazntest.domain.events

import com.madbox.dazntest.data.model.DataOrException
import com.madbox.dazntest.data.model.EventDTO
import com.madbox.dazntest.data.model.EventsDTO
import com.madbox.dazntest.data.repository.EventsRepositoryInterface
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchEventsUseCaseTest {

    private lateinit var SUT: FetchEventsUseCase

    @Test
    fun execute_withMultipleEvents() = runTest {
        val fakeRepository = FakeEventsRepository(
            events = EventsDTO().apply {
                add(
                    EventDTO(
                        "0",
                        "title",
                        "subtitle",
                        "2024-10-15T01:55:23.353Z",
                        "https://image.jpeg",
                        "https://promo.mp4"
                    )
                )
                add(
                    EventDTO(
                        "1",
                        "title2",
                        "subtitle2",
                        "2024-10-16T01:55:23.353Z",
                        "https://image2.jpeg",
                        "https://promo2.mp4"
                    )
                )
            }
        )
        SUT = FetchEventsUseCase(fakeRepository)

        val result = SUT.execute()
        assertEquals(result.size, 2)
    }

    @Test
    fun execute_withNoEvents() = runTest {
        val fakeRepository = FakeEventsRepository(
            events = EventsDTO()
        )
        SUT = FetchEventsUseCase(fakeRepository)

        val result = SUT.execute()
        assertEquals(result.size, 0)
    }

    @Test
    fun execute_withException() = runTest {
        val fakeRepository = FakeEventsRepository(
            exception = Exception("Failed to fetch events")
        )
        SUT = FetchEventsUseCase(fakeRepository)

        val result = SUT.execute()
        assertEquals(result.size, 0)
    }
}

class FakeEventsRepository(
    private val events: EventsDTO? = null,
    private val exception: Exception? = null
) : EventsRepositoryInterface {
    override suspend fun getEvents(): DataOrException<EventsDTO, Exception> {
        return if (exception != null) {
            DataOrException(null, exception) // Return an exception
        } else {
            DataOrException(
                events ?: EventsDTO(),
                null
            )
        }
    }
}


