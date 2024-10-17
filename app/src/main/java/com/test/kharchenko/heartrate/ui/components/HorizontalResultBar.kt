package com.test.kharchenko.heartrate.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalResultBar(
    modifier: Modifier = Modifier,
    position: Float,
    sectors: Int,
    sectorColors: List<Color>,
    shape: Shape,
    thumbColor: Color,
    thumbBorderColor: Color,
    thumbBorderWidth: Dp,
    thumbDpSize: DpSize,
    thumbCornerRadius: Dp,
    content: @Composable () -> Unit = {},
) {
    var thumbRelativePosition by remember { mutableFloatStateOf(0f) }
    val thumbAnimatedRelativePos = animateFloatAsState(
        thumbRelativePosition,
        animationSpec = tween(
            durationMillis = 1200,
            delayMillis = 400
        )
    )
    LaunchedEffect(key1 = Unit) {
        thumbRelativePosition = position
    }
    Box(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .heightIn(min = 20.dp)
            .drawBehind {
                val padding = 12.dp.toPx()
                val thumbSize = thumbDpSize.toSize()
                val thumbCorner = thumbCornerRadius.toPx()
                val thumbBorder = thumbBorderWidth.toPx()

                val thumbTopLeft =
                    Offset(
                        thumbAnimatedRelativePos.value * (size.width - padding ),
                        (size.height - thumbSize.height) * .5f
                    )
                drawRoundRect(
                    color = thumbColor,
                    topLeft = thumbTopLeft,
                    size = thumbSize,
                    cornerRadius = CornerRadius(thumbCorner, thumbCorner)
                )
                drawRoundRect(
                    color = thumbBorderColor,
                    topLeft = thumbTopLeft,
                    size = thumbSize,
                    style = Stroke(width = thumbBorder, cap = StrokeCap.Round),
                    cornerRadius = CornerRadius(thumbCorner, thumbCorner)
                )
            }
            .clip(shape)
            .drawBehind {
                val sectorWidth = 1f * size.width / sectors
                var startX = 0f
                var endX = startX + sectorWidth
                repeat(sectors) { index ->
                    drawRect(
                        color = sectorColors[index],
                        topLeft = Offset(startX, 0f),
                        size = Size(endX, size.height)
                    )
                    startX = endX
                    endX += sectorWidth
                }
                val padding = 12.dp.toPx()
                val thumbSize = thumbDpSize.toSize()
                val thumbCorner = thumbCornerRadius.toPx()
                val thumbBorder = thumbBorderWidth.toPx()

                val thumbTopLeft =
                    Offset(
                        thumbAnimatedRelativePos.value * (size.width - padding),
                        (size.height - thumbSize.height) * .5f
                    )
                drawRoundRect(
                    color = thumbColor,
                    topLeft = thumbTopLeft,
                    size = thumbSize,
                    cornerRadius = CornerRadius(thumbCorner, thumbCorner)
                )
                drawRoundRect(
                    color = thumbBorderColor,
                    topLeft = thumbTopLeft,
                    size = thumbSize,
                    style = Stroke(width = thumbBorder, cap = StrokeCap.Round),
                    cornerRadius = CornerRadius(thumbCorner, thumbCorner)
                )

            }
            .then(modifier),
    ) {
        content()
    }
}
