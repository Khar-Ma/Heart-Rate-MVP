package com.test.kharchenko.heartrate.domain.heartrate.model

import kotlinx.datetime.LocalDateTime

data class HeartRate(
    val id: Int,
    val bpm: Int,
    val timeStamp: LocalDateTime,
)