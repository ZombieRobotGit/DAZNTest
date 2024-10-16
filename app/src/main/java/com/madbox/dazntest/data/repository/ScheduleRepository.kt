package com.madbox.dazntest.data.repository

import com.madbox.dazntest.data.model.DataOrException
import com.madbox.dazntest.data.model.SchedulesDTO
import com.madbox.dazntest.data.network.DaznApi
import javax.inject.Inject

class ScheduleRepository @Inject constructor(private val api: DaznApi) : ScheduleRepositoryInterface {

    override suspend fun getSchedule(): DataOrException<SchedulesDTO, Exception> {
        val dataOrException = DataOrException<SchedulesDTO, Exception>()
        try {
            dataOrException.data = api.getSchedules()
        } catch (exception: Exception) {
            dataOrException.e = exception
        }
        return dataOrException
    }

}