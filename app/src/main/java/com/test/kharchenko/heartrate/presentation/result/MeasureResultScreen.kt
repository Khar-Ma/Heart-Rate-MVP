package com.test.kharchenko.heartrate.presentation.result

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate
import com.test.kharchenko.heartrate.navigation.HeartRateScreenProvider
import kotlinx.datetime.LocalDateTime

class MeasureResultScreen(private val bpm: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val historyScreen = rememberScreen(HeartRateScreenProvider.HistoryScreen)
        val menuScreen = rememberScreen(HeartRateScreenProvider.HomeScreen)

        val screenModel = koinScreenModel<MeasureResultScreenModel>()



        MeasureResultContent(
            heartRate = screenModel.addMeasurement(bpm),
            onNavigateHistory = {
                navigator.replaceAll(historyScreen)
            },
            onNavigateMenu = {
                navigator.replaceAll(menuScreen)
            })
    }
}


