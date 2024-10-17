package com.test.kharchenko.heartrate.presentation.measurement.pletysmography.utils

import androidx.camera.core.ImageProxy

fun processFrame(imageProxy: ImageProxy): Double {
    val buffer = imageProxy.planes[0].buffer
    val data = ByteArray(buffer.remaining())
    buffer.get(data)

    var redSum = 0.0
    var pixelCount = 0

    for (i in data.indices step 4) {
        val red = data[i].toInt() and 0xFF
        redSum += red
        pixelCount++
    }

    return redSum / pixelCount

}
