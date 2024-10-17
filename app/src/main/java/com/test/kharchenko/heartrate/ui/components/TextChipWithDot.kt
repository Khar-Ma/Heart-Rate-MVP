package com.test.kharchenko.heartrate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextChipWithDot(
    modifier: Modifier = Modifier,
    color: Color,
    background: Color,
    text: String,
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(4.dp))
            .background(background)
            .padding(start = 6.dp)
            .drawBehind {
                val r = size.height * .4f * .5f
                drawCircle(
                    color = color,
                    radius = r,
                    center = Offset(r, size.height * .5f)
                )
            }
            .padding(horizontal = 14.dp)
            .then(modifier)
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp,
                color = Color.Black
            )
        )
    }

}