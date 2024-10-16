package com.madbox.dazntest.domain.schedule

import com.madbox.dazntest.app.Screen
import com.madbox.dazntest.data.model.DataOrException
import com.madbox.dazntest.data.model.EventDTO
import com.madbox.dazntest.data.model.EventsDTO
import com.madbox.dazntest.data.model.ScheduleDTO
import com.madbox.dazntest.data.model.SchedulesDTO
import com.madbox.dazntest.data.repository.ScheduleRepositoryInterface
import com.madbox.dazntest.domain.events.FakeEventsRepository
import com.madbox.dazntest.domain.events.FetchEventsUseCase
import com.madbox.dazntest.domain.schedule.model.ScheduleFetchResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class FetchScheduleUseCaseTest {

    private lateinit var SUT: FetchScheduleUseCase

    @Test
    fun execute_withMultipleSchedule() = runTest {
        val fakeRepository = FakeScheduleRepository(
            events = createScheduleData()
        )
        SUT = FetchScheduleUseCase(fakeRepository)

        val result = SUT.execute()
        Assert.assertEquals(result, ScheduleFetchResult.Success(createScheduleData()))
    }

    @Test
    fun execute_withNoSchedule() = runTest {
        val fakeRepository = FakeScheduleRepository(
            events = SchedulesDTO()
        )
        SUT = FetchScheduleUseCase(fakeRepository)

        val result = SUT.execute()
        Assert.assertEquals(result, ScheduleFetchResult.Error("schedule fetch data is empty"))
    }

    @Test
    fun execute_withException() = runTest {
        val fakeRepository = FakeScheduleRepository(
            exception = Exception("Failed to fetch events")
        )
        SUT = FetchScheduleUseCase(fakeRepository)

        val result = SUT.execute()
        Assert.assertEquals(result, ScheduleFetchResult.Error("error fetching schedule data"))
    }

    private fun createScheduleData(): SchedulesDTO {
        return SchedulesDTO().apply {
            add(
                ScheduleDTO(
                    "0",
                    "title",
                    "subtitle",
                    "2024-10-15T01:55:23.353Z",
                    "https://image.jpeg"
                )
            )
            add(
                ScheduleDTO(
                    "0",
                    "title",
                    "subtitle",
                    "2024-10-15T01:55:23.353Z",
                    "https://image.jpeg"
                )
            )
        }
    }

    class FakeScheduleRepository(
        private val events: SchedulesDTO? = null,
        private val exception: Exception? = null
    ) : ScheduleRepositoryInterface {

        override suspend fun getSchedule(): DataOrException<SchedulesDTO, Exception> {
            return if (exception != null || events?.isEmpty() == true) {
                DataOrException(null, exception) // Return an exception
            } else {
                DataOrException(
                    events ?: SchedulesDTO(),
                    null
                )
            }
        }
    }
}