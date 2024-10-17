package com.test.kharchenko.heartrate.presentation.measurement.pletysmography.utils

import com.test.kharchenko.heartrate.presentation.measurement.pletysmography.Constants

fun isFingerCoveringCamera(redValue: Double): Boolean {
    return redValue > Constants.THRESHOLD
}








