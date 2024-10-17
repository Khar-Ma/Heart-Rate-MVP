package com.test.kharchenko.heartrate.navigation.di

import cafe.adriel.voyager.core.registry.screenModule
import com.test.kharchenko.heartrate.navigation.HeartRateScreenProvider
import com.test.kharchenko.heartrate.presentation.history.HistoryScreen
import com.test.kharchenko.heartrate.presentation.home.HomeScreen
import com.test.kharchenko.heartrate.presentation.measurement.HeartRateMeasureScreen
import com.test.kharchenko.heartrate.presentation.onboarding.OnboardingScreen
import com.test.kharchenko.heartrate.presentation.result.MeasureResultScreen
import com.test.kharchenko.heartrate.presentation.splashscreen.SplashScreen

val navigationModule = screenModule {
    register<HeartRateScreenProvider.SplashScreen> { SplashScreen() }
    register<HeartRateScreenProvider.OnboardingScreen> { OnboardingScreen() }
    register<HeartRateScreenProvider.HomeScreen> { HomeScreen() }
    register<HeartRateScreenProvider.HeartRateMeasureScreen> { HeartRateMeasureScreen() }
    register<HeartRateScreenProvider.HistoryScreen> { HistoryScreen() }
    register<HeartRateScreenProvider.MeasureResultScreen> { provider -> MeasureResultScreen(provider.bpm) }

}