package com.test.kharchenko.heartrate.data.heartrate.db.room

import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate

internal fun HeartRate.toEntity(): HeartRateDbEntity {
    return HeartRateDbEntity(id, bpm, timeStamp)
}

internal fun HeartRateDbEntity.toDomain(): HeartRate {
    return HeartRate(id, bpm, timeStamp)
}
