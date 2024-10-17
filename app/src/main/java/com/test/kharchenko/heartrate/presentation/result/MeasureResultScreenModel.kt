package com.test.kharchenko.heartrate.presentation.result

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.test.kharchenko.heartrate.domain.heartrate.IHeartRateRepository
import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class MeasureResultScreenModel(val heartRateRepository: IHeartRateRepository) : ScreenModel {

    fun addMeasurement(bpm: Int): HeartRate {
        val heartRate = HeartRate(
            id = 0,
            bpm = bpm,
            timeStamp = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        )
        screenModelScope.launch {
            heartRateRepository.add(heartRate)
        }
        return heartRate
    }

}