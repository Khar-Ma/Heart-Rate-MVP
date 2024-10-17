package com.test.kharchenko.heartrate.data.heartrate.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HeartRateDao {

    @Query("SELECT * FROM heart_rate_history")
    fun getHeartRateFlow(): Flow<List<HeartRateDbEntity>>

    @Insert
    suspend fun addHeartRate(heartRate: HeartRateDbEntity): Long

    @Query("DELETE FROM heart_rate_history")
    suspend fun clearAllHeartRates(): Int
}