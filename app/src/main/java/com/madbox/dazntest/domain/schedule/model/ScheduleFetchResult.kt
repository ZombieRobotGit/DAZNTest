package com.madbox.dazntest.domain.schedule.model

import com.madbox.dazntest.data.model.ScheduleDTO

sealed class ScheduleFetchResult {
    data class Success(val scheduleItem: List<ScheduleDTO>) : ScheduleFetchResult()
    data class Error(val message: String): ScheduleFetchResult()
}