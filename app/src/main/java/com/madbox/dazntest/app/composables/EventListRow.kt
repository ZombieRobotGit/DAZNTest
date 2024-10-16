package com.madbox.dazntest.app.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun EventListRow(
    imageUrl: String,
    videoUrl: String? = null,
    title: String,
    subTitle: String,
    date: String,
    onClick: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(color = Color.White).clickable {
                videoUrl?.let {
                    onClick(it)
                }
            }
    ) {
        Image(
            modifier = Modifier
                .padding(6.dp)
                .width(120.dp),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    }).build()
            ), contentDescription = "Event Image"
        )
        Column(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .weight(0.7f)
        ) {
            Text(text = title, style = TextStyle(fontSize = 18.sp))
            Text(text = subTitle, style = TextStyle(fontSize = 14.sp))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = date, color = Color.DarkGray)
        }
    }
}


@Preview
@Composable
fun EventListRowPreview() {
    EventListRow(
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310176837169_image-header_pDach_1554579780000.jpeg?alt=media&token=1777d26b-d051-4b5f-87a8-7633d3d6dd20",
        videoUrl = "",
        title = "Manchester vs Juventus",
        subTitle = "Champions League",
        date = "Yesterday, 10:30"
    ) {
    }
}