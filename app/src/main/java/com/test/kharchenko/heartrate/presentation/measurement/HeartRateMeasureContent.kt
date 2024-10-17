package com.test.kharchenko.heartrate.presentation.measurement

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.kharchenko.heartrate.R
import com.test.kharchenko.heartrate.presentation.measurement.pletysmography.PletysmographyCamera
import com.test.kharchenko.heartrate.ui.components.LinearProgressIndicatorWithText
import com.test.kharchenko.heartrate.ui.shapes.ConcaveRectangleShape
import com.test.kharchenko.heartrate.ui.theme.HeartRateTheme
import com.test.kharchenko.heartrate.ui.theme.fixedColors


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    HeartRateTheme { HeartRateMeasureContent({}, {}) }
}

@Composable
fun HeartRateMeasureContent(
    onMeasurementComplete: (bpm: Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    var bpm by remember { mutableIntStateOf(0) }
    var progress by remember { mutableIntStateOf(0) }
    var isFingerDetected by remember { mutableStateOf(false) }

    val scale by rememberInfiniteTransition(label = "inf scale").animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000 / 80 * 60,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "scale"

    )

    val primaryText =
        stringResource(if (isFingerDetected) R.string.measurement_process else R.string.measurement_finger_not_available)
    val secondaryText =
        stringResource(if (isFingerDetected) R.string.determine_pulse else R.string.measurement_put_finger)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.fixedColors.secondaryColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.End),
            onClick = onNavigateBack,
            enabled = !isFingerDetected,
            colors = IconButtonDefaults.iconButtonColors().copy(
                contentColor = MaterialTheme.fixedColors.gray2,
                disabledContentColor = Color.Transparent
            )
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "close"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            PletysmographyCamera(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.fixedColors.blue2,
                        shape = CircleShape
                    ),
                onFingerNotAvailable = {
                    isFingerDetected = false
                    bpm = 0
                    progress = 0
                },
                onProcess = { estimatedBpm: Int, progres: Int, fingerDetected: Boolean ->
                    isFingerDetected = fingerDetected
                    bpm = estimatedBpm
                    progress = progres

                },
                onMeasured = { resultBpm: Int ->
                    bpm = resultBpm
                    onMeasurementComplete(bpm)
                }
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = primaryText,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Black,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = secondaryText,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.fixedColors.onSecondary,
                    fontSize = 16.sp
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .aspectRatio(1f)
                    .scale(if (isFingerDetected) scale else 1f),
                painter = painterResource(R.drawable.ic_measure_background),
                contentDescription = "heart"
            )
            Column(
                modifier = Modifier.offset(10.dp, (-30).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${if (isFingerDetected) bpm else "--"}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = Color.White,
                        fontSize = 74.sp
                    )
                )
                Text(
                    text = stringResource(R.string.bpm),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontSize = 22.sp
                    )
                )

            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .aspectRatio(1f)
                    .graphicsLayer {
                        translationX = 80f
                    }
                    .alpha(if (!isFingerDetected) 1f else 0f),
                painter = painterResource(R.drawable.img_hands_phone),
                contentDescription = "hand and phone"
            )
            Box(modifier = Modifier.alpha(if (isFingerDetected) 1f else 0f)) {
                LinearProgressIndicatorWithText(
                    progress = progress * 0.01f,
                    color = MaterialTheme.fixedColors.primaryColor,
                    trackColor = MaterialTheme.fixedColors.primaryHalfColor,
                    fontSize = 16.sp,
                    verticalPadding = 0.dp
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(
                    color = Color.White,
                    shape = ConcaveRectangleShape(200f)
                )
        )

    }
}
