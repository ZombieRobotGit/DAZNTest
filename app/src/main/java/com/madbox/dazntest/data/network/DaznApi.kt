package com.madbox.dazntest.data.network

import com.madbox.dazntest.data.model.EventsDTO
import com.madbox.dazntest.data.model.SchedulesDTO
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface DaznApi {

    @GET("getEvents")
    suspend fun getEvents(): EventsDTO

    @GET("getSchedule")
    suspend fun getSchedules(): SchedulesDTO

}