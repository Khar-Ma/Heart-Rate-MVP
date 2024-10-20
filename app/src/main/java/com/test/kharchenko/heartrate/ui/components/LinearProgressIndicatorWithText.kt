package com.test.kharchenko.heartrate.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.kharchenko.heartrate.ui.theme.fixedColors

@Composable
fun LinearProgressIndicatorWithText(
    progress: Float,
    trackColor: Color,
    color: Color,
    fontSize: TextUnit = 16.sp,
    verticalPadding: Dp = 6.dp,
    shape: Shape = RoundedCornerShape(8.dp),
) {
    val textHeight: Dp = with(LocalDensity.current) { fontSize.toDp() }
    val progressBarHeight = textHeight + verticalPadding

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape)
    ) {
        LinearProgressIndicator(
            progress = { progress },
            color = color,
            trackColor = trackColor,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .fillMaxWidth()
                .height(progressBarHeight)
                .border(1.dp, MaterialTheme.fixedColors.red2, shape = shape)
        )
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = fontSize,
                lineHeight = fontSize
            ),
            color = Color.White
        )
    }
}
