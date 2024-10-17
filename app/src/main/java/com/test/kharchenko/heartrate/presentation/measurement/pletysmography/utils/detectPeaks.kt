package com.test.kharchenko.heartrate.presentation.measurement.pletysmography.utils

fun detectPeaks(values: List<Double>): Int {
    var peaks = 0
    for (i in 1 until values.size - 1) {
        if (values[i] > values[i - 1] && values[i] > values[i + 1]) {
            peaks++
        }
    }
    return peaks
}
