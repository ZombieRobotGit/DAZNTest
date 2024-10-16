package com.madbox.dazntest.di

import com.madbox.dazntest.data.network.DaznApi
import com.madbox.dazntest.data.repository.EventsRepository
import com.madbox.dazntest.app.utils.Constants
import com.madbox.dazntest.data.repository.EventsRepositoryInterface
import com.madbox.dazntest.data.repository.ScheduleRepository
import com.madbox.dazntest.data.repository.ScheduleRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideQuestionApi(): DaznApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(DaznApi::class.java)
    }


    @Provides
    @Singleton
    fun provideEventsRepository(api: DaznApi): EventsRepositoryInterface {
        return EventsRepository(api)
    }

    @Provides
    @Singleton
    fun provideScheduleRepository(api: DaznApi): ScheduleRepositoryInterface {
        return ScheduleRepository(api)
    }

}