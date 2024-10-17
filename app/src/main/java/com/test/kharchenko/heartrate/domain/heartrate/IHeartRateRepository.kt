package com.test.kharchenko.heartrate.domain.heartrate

import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate
import kotlinx.coroutines.flow.Flow

interface IHeartRateRepository {
    fun getHeartRateFlow(): Flow<List<HeartRate>>
    suspend fun add(heartRate: HeartRate): Boolean
    suspend fun clear(): Boolean
}