package com.test.kharchenko.heartrate.presentation.splashscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.test.kharchenko.heartrate.R
import com.test.kharchenko.heartrate.navigation.HeartRateScreenProvider
import com.test.kharchenko.heartrate.ui.components.LinearProgressIndicatorWithText
import com.test.kharchenko.heartrate.ui.shapes.ConcaveRectangleShape
import com.test.kharchenko.heartrate.ui.theme.HeartRateTheme
import com.test.kharchenko.heartrate.ui.theme.fixedColors

class SplashScreen : Screen {
    @Preview(showSystemUi = true)
    @Composable
    private fun Preview() {
        HeartRateTheme { Content() }
    }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val onboardingScreen = rememberScreen(HeartRateScreenProvider.OnboardingScreen)

        val targetValue = remember { mutableFloatStateOf(0f) }
        val progress by animateFloatAsState(
            targetValue = targetValue.floatValue,
            animationSpec = tween(2000),
            finishedListener = {
                navigator.replace(onboardingScreen)
            }
        )
        LaunchedEffect(key1 = Unit) {
            targetValue.value = 1f
        }


        Column(
            modifier = Modifier
                .background(color = MaterialTheme.fixedColors.secondaryColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(R.drawable.img_heart),
                    contentDescription = "heart"
                )
                Text(
                    text = stringResource(R.string.loading_screen),
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 54.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = ConcaveRectangleShape(100f)
                    )
                    .padding(horizontal = 30.dp, vertical = 60.dp),
            ) {
                LinearProgressIndicatorWithText(
                    progress = progress,
                    color = MaterialTheme.fixedColors.primaryColor,
                    trackColor = MaterialTheme.fixedColors.primaryHalfColor,
                    fontSize = 14.sp,
                    verticalPadding = 0.dp
                )

            }
        }
    }

}

