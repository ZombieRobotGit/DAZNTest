package com.madbox.dazntest.data.repository

import com.madbox.dazntest.data.model.DataOrException
import com.madbox.dazntest.data.model.EventsDTO

interface EventsRepositoryInterface {
    suspend fun getEvents(): DataOrException<EventsDTO, Exception>
}