package com.test.kharchenko.heartrate.data.heartrate.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.datetime.LocalDateTime

@Database(entities = [HeartRateDbEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heartRateDao(): HeartRateDao
}
