package com.test.kharchenko.heartrate.presentation.measurement.pletysmography

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.test.kharchenko.heartrate.presentation.measurement.pletysmography.camerax.CameraPreview
import com.test.kharchenko.heartrate.presentation.measurement.pletysmography.model.MeasuringSession
import com.test.kharchenko.heartrate.presentation.measurement.pletysmography.utils.calculatePulse
import com.test.kharchenko.heartrate.presentation.measurement.pletysmography.utils.isFingerCoveringCamera
import com.test.kharchenko.heartrate.presentation.measurement.pletysmography.utils.processFrame

@Composable
fun PletysmographyCamera(
    modifier: Modifier = Modifier,
    onFingerNotAvailable: () -> Unit,
    onProcess: (estimatedBpm: Int, progress: Int, isFingerDetected: Boolean) -> Unit,
    onMeasured: (bpm: Int) -> Unit,
) {
    var isFingerDetected by remember { mutableStateOf(false) }
    var heartRate by remember { mutableIntStateOf(0) }

    var redValue by remember { mutableDoubleStateOf(0.0) }
    var redValuesList by remember { mutableStateOf(listOf<Double>()) }

    var measuringState by remember { mutableStateOf<MeasuringSession>(MeasuringSession.FingerNotAvailable) }

    var startTime by remember { mutableLongStateOf(0L) }
    var currentTime by remember { mutableLongStateOf(0L) }
    CameraPreview(
        modifier = modifier,
        onFrameCaptured = { imageProxy ->
            redValue = processFrame(imageProxy)
            isFingerDetected = isFingerCoveringCamera(redValue)

            when (measuringState) {
                MeasuringSession.FingerNotAvailable -> {
                    onFingerNotAvailable()
                    redValuesList = listOf()
                    redValue = 0.0
                    heartRate = 0
                    startTime = 0L
                    currentTime = 0L
                    if (isFingerDetected) {
                        measuringState = MeasuringSession.Measuring
                        startTime = System.currentTimeMillis()
                    }
                }

                MeasuringSession.Measuring -> {
                    if (!isFingerDetected) {
                        measuringState = MeasuringSession.FingerNotAvailable
                        return@CameraPreview
                    }
                    currentTime =
                        System.currentTimeMillis()
                    val timePassed = currentTime - startTime
                    if (timePassed > Constants.SAMPLE_DURATION * 1000L) {
                        measuringState = MeasuringSession.Measured(heartRate)
                    }
                    redValuesList = redValuesList.plus(redValue)
                    val bpm = calculatePulse(
                        redValuesList,
                        (timePassed / 1000.0)
                    )
                    heartRate = bpm.toInt()
                    onProcess(
                        heartRate,
                        (((currentTime - startTime) * 1f / (Constants.SAMPLE_DURATION * 1000f)) * 100f).toInt(),
                        isFingerDetected
                    )
                }

                is MeasuringSession.Measured -> {
                    onMeasured((measuringState as MeasuringSession.Measured).bpm)
                }
            }
            imageProxy.close()
        },
        onCameraControl = { _, control ->
            control.enableTorch(true)
        }
    )
}
