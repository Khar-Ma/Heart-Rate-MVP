package com.test.kharchenko.heartrate.presentation.measurement.pletysmography.utils

fun lowPassFilter(data: List<Double>): List<Double> {
    val alpha = 0.1
    val filtered = mutableListOf<Double>()
    var previousValue = data[0]

    for (value in data) {
        previousValue += alpha * (value - previousValue)
        filtered.add(previousValue)
    }
    return filtered
}

