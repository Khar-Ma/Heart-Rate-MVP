package com.test.kharchenko.heartrate.presentation.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.kharchenko.heartrate.R
import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate
import com.test.kharchenko.heartrate.ui.components.HorizontalResultBar
import com.test.kharchenko.heartrate.ui.components.TextChipWithDot
import com.test.kharchenko.heartrate.ui.theme.fixedColors

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun MeasurementResultCard(
    heartRate: HeartRate,
) {
    val formattedDateTime =
        "${heartRate.timeStamp.time.hour}:${heartRate.timeStamp.time.minute}\n" +
                "${heartRate.timeStamp.date.dayOfMonth}/${heartRate.timeStamp.date.monthNumber}/${heartRate.timeStamp.date.year}"

    val gray = MaterialTheme.fixedColors.gray3
    val textStyle = MaterialTheme.typography.bodySmall.copy(
        fontSize = 12.sp
    )

    var less60 by remember { mutableStateOf(gray) }
    var between60n100 by remember { mutableStateOf(gray) }
    var more100 by remember { mutableStateOf(gray) }

    val resultText: String
    val resultColor: Color
    when {
        heartRate.bpm < 60 -> {
            resultText = stringResource(R.string.pulse_slow)
            less60 = Color.Black
            resultColor = MaterialTheme.fixedColors.blue2

        }

        heartRate.bpm in 60..100 -> {
            resultText = stringResource(R.string.pulse_normal)
            between60n100 = Color.Black
            resultColor = MaterialTheme.fixedColors.green2
        }

        else -> {
            resultText = stringResource(R.string.pulse_enlarged)
            more100 = Color.Black
            resultColor = MaterialTheme.fixedColors.red2
        }

    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.fixedColors.surfaceColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 20.dp, vertical = 30.dp),
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f),

                    ) {
                    Text(
                        modifier = Modifier
                            .wrapContentSize(),
                        text = stringResource(R.string.your_result),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize(),
                        text = resultText,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.displaySmall.copy(
                            color = resultColor,
                            fontSize = 28.sp
                        )
                    )

                }
                Row(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.access_time),
                        tint = MaterialTheme.fixedColors.gray3,
                        contentDescription = "access time"
                    )
                    Spacer(Modifier.size(10.dp))
                    Text(
                        modifier = Modifier
                            .wrapContentSize(),
                        text = formattedDateTime,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.fixedColors.gray3,
                            fontSize = 16.sp
                        )
                    )
                }
            }
            Spacer(Modifier.size(20.dp))
            HorizontalResultBar(
                position = ((heartRate.bpm - 20).coerceIn(0, 120) / 120f),
                sectors = 3,
                sectorColors = listOf(
                    MaterialTheme.fixedColors.blue,
                    MaterialTheme.fixedColors.green,
                    MaterialTheme.fixedColors.red,
                ),
                shape = RoundedCornerShape(12.dp),
                thumbColor = Color.White,
                thumbDpSize = DpSize(10.dp, 34.dp),
                thumbCornerRadius = 12.dp,
                thumbBorderColor = Color.Black,
                thumbBorderWidth = .5.dp,
            )
            Spacer(Modifier.size(20.dp))
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                maxItemsInEachRow = 2,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                TextChipWithDot(
                    text = stringResource(R.string.pulse_slow),
                    color = MaterialTheme.fixedColors.blue2,
                    background = MaterialTheme.fixedColors.secondaryColor,
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize(),
                    text = stringResource(R.string.less_than_60_bpm),
                    style = textStyle.copy(color = less60)
                )
                TextChipWithDot(
                    text = stringResource(R.string.pulse_normal),
                    color = MaterialTheme.fixedColors.green2,
                    background = MaterialTheme.fixedColors.secondaryColor,
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize(),
                    text = stringResource(R.string.between_60_100_bpm),
                    style = textStyle.copy(color = between60n100)
                )
                TextChipWithDot(
                    text = stringResource(R.string.pulse_enlarged),
                    color = MaterialTheme.fixedColors.red2,
                    background = MaterialTheme.fixedColors.secondaryColor,
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize(),
                    text = stringResource(R.string.more_than_100_bpm),
                    style = textStyle.copy(color = more100)
                )

            }
        }
    }
}
