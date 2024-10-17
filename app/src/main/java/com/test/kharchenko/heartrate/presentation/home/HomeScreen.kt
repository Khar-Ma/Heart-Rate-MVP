package com.test.kharchenko.heartrate.presentation.home

import android.widget.Space
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.test.kharchenko.heartrate.R
import com.test.kharchenko.heartrate.navigation.HeartRateScreenProvider
import com.test.kharchenko.heartrate.ui.shapes.ConcaveRectangleShape
import com.test.kharchenko.heartrate.ui.theme.HeartRateTheme
import com.test.kharchenko.heartrate.ui.theme.fixedColors

class HomeScreen : Screen {
    @Preview(showSystemUi = true)
    @Composable
    private fun Preview() {
        HeartRateTheme { Content() }
    }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val historyScreen = rememberScreen(HeartRateScreenProvider.HistoryScreen)
        val measureScreen = rememberScreen(HeartRateScreenProvider.HeartRateMeasureScreen)


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
                            navigator.push(historyScreen)
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
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.home_title),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = 28.sp
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.img_heart),
                    contentDescription = "heart"
                )
            }
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
                IconButton(
                    modifier = Modifier
                        .sizeIn(100.dp, 100.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    colors = IconButtonDefaults.iconButtonColors().copy(
                        containerColor = MaterialTheme.fixedColors.primaryColor,
                        contentColor = Color.White,

                        ),
                    onClick = {
                        navigator.push(measureScreen)
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .wrapContentSize(),
                        painter = painterResource(R.drawable.ic_heart),
                        contentDescription = "heart",
                    )
                }
            }
        }
    }
}