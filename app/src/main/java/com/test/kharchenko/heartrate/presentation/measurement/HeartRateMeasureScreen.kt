package com.test.kharchenko.heartrate.presentation.measurement

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.test.kharchenko.heartrate.navigation.HeartRateScreenProvider


class HeartRateMeasureScreen : Screen {

    @Composable
    override fun Content() {
        val ctx = LocalContext.current
        val cameraGranted =
            ctx.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        val isGranted = remember { mutableStateOf(cameraGranted) }

        val navigator = LocalNavigator.currentOrThrow

        if (!isGranted.value) RequestCameraPermission { isGranted.value = it }
        if (isGranted.value) HeartRateMeasureContent(
            onMeasurementComplete = { bpm ->
                navigator.replaceAll(
                    ScreenRegistry.get(HeartRateScreenProvider.MeasureResultScreen(bpm))
                )
            },
            onNavigateBack = {
                navigator.pop()
            },
        )
    }
}



