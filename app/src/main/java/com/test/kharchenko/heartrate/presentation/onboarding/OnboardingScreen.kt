@file:OptIn(ExperimentalFoundationApi::class)

package com.test.kharchenko.heartrate.presentation.onboarding

import DotsIndicator
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.test.kharchenko.heartrate.R
import com.test.kharchenko.heartrate.navigation.HeartRateScreenProvider
import com.test.kharchenko.heartrate.ui.components.OnboardingCardContent
import com.test.kharchenko.heartrate.ui.shapes.ConcaveRectangleShape
import com.test.kharchenko.heartrate.ui.theme.HeartRateTheme
import com.test.kharchenko.heartrate.ui.theme.fixedColors
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class OnboardingScreen : Screen {
    @Preview(
        apiLevel = 34,
        showSystemUi = true,
        uiMode = Configuration.UI_MODE_TYPE_NORMAL
    )
    @Composable
    private fun Preview() {
        HeartRateTheme { Content() }
    }

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()

        val navigator = LocalNavigator.currentOrThrow
        val homeScreen = rememberScreen(HeartRateScreenProvider.HomeScreen)

        val pagerState = rememberPagerState(pageCount = { 3 })
        val cards = onboardingCardsList

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.fixedColors.secondaryColor)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                val card = cards[page]
                val pageOffset =
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

                val scale = lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                )

                OnboardingCardContent(
                    onboardingCard = card,
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = Color.White,
                        shape = ConcaveRectangleShape(100f)
                    )
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.size(40.dp))
                DotsIndicator(cards.size, pagerState.currentPage)
                Spacer(Modifier.size(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = MaterialTheme.fixedColors.primaryColor,
                        disabledContainerColor = MaterialTheme.fixedColors.primaryHalfColor,
                    ),
                    onClick = {
                        when {
                            pagerState.currentPage < cards.size - 1 -> {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(
                                        (pagerState.currentPage + 1).coerceAtMost(
                                            cards.size - 1
                                        )
                                    )
                                }

                            }

                            else -> {
                                navigator.replace(homeScreen)
                            }
                        }
                    }
                ) {
                    Text(
                        stringResource(R.string.onboarding_start),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                Spacer(Modifier.size(40.dp))
            }
        }
    }
}



