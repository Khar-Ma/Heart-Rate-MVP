package com.test.kharchenko.heartrate.data.heartrate.db.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "heart_rate_history")
data class HeartRateDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bpm: Int,
    val timeStamp: LocalDateTime
)