package com.madbox.dazntest.data.repository

import com.madbox.dazntest.data.model.DataOrException
import com.madbox.dazntest.data.model.EventsDTO
import com.madbox.dazntest.data.network.DaznApi
import javax.inject.Inject

class EventsRepository @Inject constructor(private val api: DaznApi) : EventsRepositoryInterface {

    override suspend fun getEvents(): DataOrException<EventsDTO, Exception> {
        val dataOrException = DataOrException<EventsDTO, Exception>()
        try {
            dataOrException.data = api.getEvents()
        } catch (exception: Exception) {
            dataOrException.e = exception
        }

        return dataOrException

    }

}