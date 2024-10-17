package com.test.kharchenko.heartrate.presentation.history

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.kharchenko.heartrate.R
import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate
import com.test.kharchenko.heartrate.ui.components.scrollbar.LazyColumnScrollbar
import com.test.kharchenko.heartrate.ui.components.scrollbar.ScrollbarSelectionMode
import com.test.kharchenko.heartrate.ui.theme.HeartRateTheme
import com.test.kharchenko.heartrate.ui.theme.fixedColors
import kotlinx.datetime.LocalDateTime


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    HeartRateTheme {
        HistoryContent(listOf(
            HeartRate(
                0, 120,
                LocalDateTime(2024, 12, 12, 12, 10)
            ),
        ), {}, {})
    }
}

@Composable
fun HistoryContent(
    heartRate: List<HeartRate>,
    onNavigateMenu: () -> Unit,
    onClean: () -> Unit,
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
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable(
                        role = Role.Button,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = LocalIndication.current
                    ) {
                        onNavigateMenu()
                    },
            ) {
                val contentColor = Color.White
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.ic_arrow_back),
                    tint = contentColor,
                    contentDescription = "back"
                )
                Spacer(Modifier.size(10.dp))
                Text(
                    text = stringResource(R.string.history_btn_label),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 20.sp,
                        color = contentColor
                    )
                )
            }
        }
        Box {
            val listState = rememberLazyListState()
            LazyColumnScrollbar(
                listState = listState,
                thumbColor = MaterialTheme.fixedColors.primaryColor,
                thumbSelectedColor = MaterialTheme.fixedColors.red2,
                trackColor = MaterialTheme.fixedColors.primaryHalfColor,
                trackVerticalPadding = 30.dp,
                selectionMode = ScrollbarSelectionMode.Thumb,
                alwaysShowScrollBar = true,
            ) {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(
                        start = 16.dp, end = 24.dp, bottom = 20.dp, top = 20.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(heartRate.size) { index ->
                        HeartRateCard(heartRate.get(index))
                    }
                    item {
                        Button(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp),
                            colors = ButtonDefaults.buttonColors().copy(
                                containerColor = MaterialTheme.fixedColors.primaryColor
                            ),
                            onClick = {
                                onClean()
                            }) {
                            Text(
                                text = stringResource(R.string.history_clear),
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            )
                        }
                    }
                }
            }

        }

    }
}
