package com.madbox.dazntest.data.repository

import com.madbox.dazntest.data.model.DataOrException
import com.madbox.dazntest.data.model.SchedulesDTO

interface ScheduleRepositoryInterface {
    suspend fun getSchedule(): DataOrException<SchedulesDTO, Exception>
}