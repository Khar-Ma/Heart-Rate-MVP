package com.test.kharchenko.heartrate.presentation.measurement.pletysmography.model

sealed class MeasuringSession {
    data object FingerNotAvailable : MeasuringSession()
    data object Measuring : MeasuringSession()
    data class Measured(val bpm: Int) : MeasuringSession()
}
