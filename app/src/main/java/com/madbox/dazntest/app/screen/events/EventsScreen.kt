package com.madbox.dazntest.app.screen.events

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.madbox.dazntest.app.composables.EventListRow
import com.madbox.dazntest.app.utils.DateConverterHelper

@Composable
fun EventsScreen(onEventSelected: (String) -> Unit) {

    val eventsViewModel: EventsViewModel = hiltViewModel()
    val events = eventsViewModel.eventsList.value

    LaunchedEffect(Unit) {
        eventsViewModel.fetchEvents()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(events) { item ->
            EventListRow(
                imageUrl = item.imageUrl,
                videoUrl = item.videoUrl,
                title = item.title,
                subTitle = item.subtitle,
                date = DateConverterHelper.formatTimestamp(item.date)
            ) {
                onEventSelected(it)
            }
        }
    }
}