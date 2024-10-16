package com.madbox.dazntest.app.screen.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.madbox.dazntest.app.composables.EventListRow
import com.madbox.dazntest.app.utils.DateConverterHelper

@Composable
fun ScheduleScreen(scheduleViewModel: ScheduleViewModel = hiltViewModel()) {

    val list = scheduleViewModel.data.collectAsState().value
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        scheduleViewModel.fetchDataPeriodically()
        listState.scrollToItem(0)
    }

    DisposableEffect(Unit) {
        onDispose {
            scheduleViewModel.cancelRefreshPeriodicallyCoroutine()
        }
    }


    Column {
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            items(list ?: emptyList()) {
                EventListRow(
                    imageUrl = it.imageUrl,
                    title = it.title,
                    subTitle = it.subtitle,
                    date = DateConverterHelper.formatTimestamp(it.date)
                )
            }
        }

    }
}