package com.test.kharchenko.heartrate.presentation.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate
import com.test.kharchenko.heartrate.ui.theme.fixedColors

@Composable
fun HeartRateCard(
    value: HeartRate,
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        val localDateTime = value.timeStamp
        val formattedDateTime = "${localDateTime.time.hour}:${localDateTime.time.minute}\n" +
                "${localDateTime.date.dayOfMonth}/${localDateTime.date.monthNumber}/${localDateTime.date.year}"

        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f),
                text = "${value.bpm} BPM",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontSize = 36.sp
                )
            )
            VerticalDivider(
                thickness = 4.dp,
                color = MaterialTheme.fixedColors.primaryColor,
                modifier = Modifier
                    .height(80.dp)
                    .padding(horizontal = 4.dp, vertical = 8.dp)
            )
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f),
                text = formattedDateTime,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.fixedColors.gray3,
                    fontSize = 24.sp
                )
            )
        }
    }

}