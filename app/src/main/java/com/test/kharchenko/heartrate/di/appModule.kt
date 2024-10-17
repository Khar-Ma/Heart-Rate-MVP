package com.test.kharchenko.heartrate.di

import HeartRateRepositoryImpl
import android.app.Application
import androidx.room.Room
import com.test.kharchenko.heartrate.data.heartrate.db.room.AppDatabase
import com.test.kharchenko.heartrate.data.heartrate.db.room.HeartRateDao
import com.test.kharchenko.heartrate.domain.heartrate.IHeartRateRepository
import com.test.kharchenko.heartrate.presentation.history.HistoryScreenModel
import com.test.kharchenko.heartrate.presentation.result.MeasureResultScreenModel
import org.koin.dsl.module

val appModule = module {
    factory<HistoryScreenModel> { HistoryScreenModel(heartRateRepository = get()) }
    factory<MeasureResultScreenModel> { MeasureResultScreenModel(heartRateRepository = get()) }

    single<IHeartRateRepository> {
        HeartRateRepositoryImpl(get())
    }
    single<HeartRateDao> { get<AppDatabase>().heartRateDao() }

    single<AppDatabase> {
        Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "heart_rate_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}