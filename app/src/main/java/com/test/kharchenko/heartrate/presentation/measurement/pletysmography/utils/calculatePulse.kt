package com.test.kharchenko.heartrate.presentation.measurement.pletysmography.utils

fun calculatePulse(redValues: List<Double>, timeInSeconds: Double): Double {
    val filteredValues = lowPassFilter(redValues)
    val peaks = detectPeaks(filteredValues)
    val pulseRate = (1.0 * peaks / timeInSeconds) * 60.0  // BPM
    return pulseRate
}
