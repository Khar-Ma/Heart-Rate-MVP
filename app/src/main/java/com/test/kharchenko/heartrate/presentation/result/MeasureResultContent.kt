package com.test.kharchenko.heartrate.presentation.result

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.kharchenko.heartrate.R
import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate
import com.test.kharchenko.heartrate.ui.shapes.ConcaveRectangleShape
import com.test.kharchenko.heartrate.ui.theme.HeartRateTheme
import com.test.kharchenko.heartrate.ui.theme.fixedColors
import kotlinx.datetime.LocalDateTime


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    HeartRateTheme { MeasureResultContent(HeartRate(1, 100, LocalDateTime(1, 1, 1, 1, 1)), {}, {}) }
}

@Composable
fun MeasureResultContent(
    heartRate: HeartRate,
    onNavigateHistory: () -> Unit,
    onNavigateMenu: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.fixedColors.secondaryColor)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(color = MaterialTheme.fixedColors.primaryColor)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable(
                        role = Role.Button,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = LocalIndication.current
                    ) {
                        onNavigateHistory()
                    },
            ) {
                val contentColor = Color.White
                Text(
                    text = stringResource(R.string.history_btn_label),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 20.sp,
                        color = contentColor
                    )
                )
                Spacer(Modifier.size(10.dp))
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.ic_history),
                    tint = contentColor,
                    contentDescription = "history"
                )
            }
        }
        Spacer(modifier = Modifier.size(40.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            MeasurementResultCard(heartRate)
        }
        Spacer(modifier = Modifier.size(40.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    shape = ConcaveRectangleShape(),
                    color = Color.White
                )
                .padding(vertical = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.fixedColors.primaryColor,
                    disabledContainerColor = MaterialTheme.fixedColors.primaryHalfColor,
                ),
                onClick = {
                    onNavigateMenu()
                }
            ) {
                Text(
                    stringResource(R.string.measurement_done),
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

        }
    }
}


