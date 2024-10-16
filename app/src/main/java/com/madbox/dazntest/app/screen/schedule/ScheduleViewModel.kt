package com.madbox.dazntest.app.screen.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madbox.dazntest.app.model.Schedule
import com.madbox.dazntest.app.utils.DateConverterHelper
import com.madbox.dazntest.domain.schedule.FetchScheduleUseCase
import com.madbox.dazntest.domain.schedule.model.ScheduleFetchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val fetchScheduleUseCase: FetchScheduleUseCase
) : ViewModel() {

    private val _data = MutableStateFlow<List<Schedule>?>(null)
    val data: StateFlow<List<Schedule>?> = _data

    private var refreshingJob: Job? = null

    fun cancelRefreshPeriodicallyCoroutine() {
        viewModelScope.launch {
            refreshingJob?.cancelAndJoin()
        }
    }

    fun fetchDataPeriodically() {
        refreshingJob = viewModelScope.launch {
            while (true) {
                if (!isActive) break
                val data = fetchScheduleUseCase.execute()
                if (data is ScheduleFetchResult.Success) {
                     val mappedData = data.scheduleItem.map {
                        Schedule(
                            id = it.id,
                            title = it.title,
                            subtitle = it.subtitle,
                            date = DateConverterHelper.convertToTimestamp(
                                it.date
                            ),
                            imageUrl = it.imageUrl,
                        )
                    }.sortedBy { it.date }
                    _data.value = mappedData
                }

                delay(3000)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            refreshingJob?.cancelAndJoin()
        }
    }
}