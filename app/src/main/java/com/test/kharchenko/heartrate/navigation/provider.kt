package com.test.kharchenko.heartrate.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class HeartRateScreenProvider : ScreenProvider {
    data object SplashScreen : HeartRateScreenProvider()
    data object OnboardingScreen : HeartRateScreenProvider()
    data object HomeScreen : HeartRateScreenProvider()
    data object HeartRateMeasureScreen : HeartRateScreenProvider()
    data object HistoryScreen : HeartRateScreenProvider()
    data class MeasureResultScreen(val bpm: Int) : HeartRateScreenProvider()

}