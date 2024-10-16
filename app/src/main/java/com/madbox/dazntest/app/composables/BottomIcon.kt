package com.madbox.dazntest.app.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun RowScope.BottomIcon(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val itemColor = if (isSelected) {
        Color.Black
    } else {
        Color.LightGray
    }

    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(2.dp, color = itemColor),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .width(34.dp)
                .height(34.dp)
        ) {}
        Text(
            text = label.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = itemColor)
        )
    }
}