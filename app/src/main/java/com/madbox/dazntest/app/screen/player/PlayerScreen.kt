package com.madbox.dazntest.app.screen.player

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.madbox.dazntest.app.composables.VideoPlayer

@Composable
fun PlayerScreen() {
    val lifecycleOwner = LocalLifecycleOwner.current
    VideoPlayer(
        modifier = Modifier
            .height(500.dp)
            .width(200.dp),
        videoUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media",
        lifecycle = lifecycleOwner.lifecycle
    )
}