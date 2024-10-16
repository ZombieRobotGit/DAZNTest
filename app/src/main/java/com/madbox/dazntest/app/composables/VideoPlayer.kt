package com.madbox.dazntest.app.composables

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier,
    lifecycle: Lifecycle,

) {
    val context = LocalContext.current
    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    AndroidView(
        modifier = modifier,
        factory = {
            PlayerView(context).apply {
                exoPlayer = ExoPlayer.Builder(context).build().also { exoPlayer ->
                    val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.prepare()
                    exoPlayer.playWhenReady = true
                    player = exoPlayer
                }
            }
        },
        update = { view ->
            view.player = exoPlayer
        }
    )


//    DisposableEffect(
//        key1 = Unit
//    ) {
//        onDispose {
//            exoPlayer?.release()
//        }
//    }

    // Manage the player state based on the lifecycle events.
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> exoPlayer?.pause()
                Lifecycle.Event.ON_RESUME -> exoPlayer?.play()
                Lifecycle.Event.ON_DESTROY -> exoPlayer?.pause()
                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}