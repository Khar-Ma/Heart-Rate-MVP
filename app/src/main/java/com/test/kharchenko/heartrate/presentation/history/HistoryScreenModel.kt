package com.test.kharchenko.heartrate.presentation.history

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.test.kharchenko.heartrate.domain.heartrate.IHeartRateRepository
import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HistoryScreenModel(private val heartRateRepository: IHeartRateRepository) :
    StateScreenModel<List<HeartRate>>(listOf()) {

    init {
        heartRateRepository.getHeartRateFlow().onEach { list ->
            mutableState.value = list
        }.launchIn(screenModelScope)
    }


    fun clearHistory() {
        screenModelScope.launch {
            heartRateRepository.clear()
        }
    }
}