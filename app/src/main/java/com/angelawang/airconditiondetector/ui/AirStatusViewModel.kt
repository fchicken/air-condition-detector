package com.angelawang.airconditiondetector.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.angelawang.airconditiondetector.database.AirStatus
import com.angelawang.airconditiondetector.database.AirStatusDatabase
import com.angelawang.airconditiondetector.database.AirStatusRepository
import kotlinx.coroutines.flow.*

class AirStatusViewModel(application: Application): AndroidViewModel(application) {

    private val repository: AirStatusRepository = AirStatusRepository(application)
    private val allStatus: LiveData<List<AirStatus>> = repository.getAll()
    fun getAllStatus(): LiveData<List<AirStatus>> {
        return allStatus
    }

    val searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val searchFlow = searchQuery.flatMapLatest {
        if(it.isEmpty()) {
            flowOf(listOf())
        } else {
            repository.search(it)
        }
    }
    val searchResult: LiveData<List<AirStatus>> = searchFlow.asLiveData()
}