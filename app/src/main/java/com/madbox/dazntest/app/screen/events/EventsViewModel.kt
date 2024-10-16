package com.madbox.dazntest.app.screen.events

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madbox.dazntest.app.model.Event
import com.madbox.dazntest.app.utils.DateConverterHelper
import com.madbox.dazntest.domain.events.FetchEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val fetchEventsUseCase: FetchEventsUseCase
) :
    ViewModel() {

    val eventsList: MutableState<List<Event>> =
        mutableStateOf(
            emptyList()
        )

    fun fetchEvents() {
        viewModelScope.launch {
            eventsList.value = fetchEventsUseCase.execute().map {
                Event(
                    id = it.id,
                    title = it.title,
                    subtitle = it.subtitle,
                    date = DateConverterHelper.convertToTimestamp(
                        it.date
                    ),
                    imageUrl = it.imageUrl,
                    videoUrl = it.videoUrl
                )
            }.sortedBy { it.date }
        }
    }
}