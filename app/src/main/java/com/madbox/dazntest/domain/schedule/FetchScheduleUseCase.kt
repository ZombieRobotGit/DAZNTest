package com.madbox.dazntest.domain.schedule

import com.madbox.dazntest.data.model.ScheduleDTO
import com.madbox.dazntest.data.repository.ScheduleRepositoryInterface
import com.madbox.dazntest.domain.schedule.model.ScheduleFetchResult
import javax.inject.Inject

class FetchScheduleUseCase @Inject constructor(private val repository: ScheduleRepositoryInterface) {

    suspend fun execute(): ScheduleFetchResult {

        val fetchedData = repository.getSchedule()

        if (fetchedData.e != null) {
            return ScheduleFetchResult.Error(
                message = "error fetching schedule data"
            )
        }

        val mappedModel = fetchedData.data?.map {
            ScheduleDTO(
                id = it.id,
                title = it.title,
                subtitle = it.subtitle,
                date = it.date,
                imageUrl = it.imageUrl
            )
        }

        return mappedModel?.let { ScheduleFetchResult.Success(it) } ?: ScheduleFetchResult.Error(
            message = "schedule fetch data is empty"
        )
    }

}