package com.angelawang.airconditiondetector.ui

import android.app.Application
import androidx.lifecycle.*
import com.angelawang.airconditiondetector.data.model.AirStatus
import com.angelawang.airconditiondetector.data.AirStatusRepository
import kotlinx.coroutines.flow.*

class AirStatusViewModel(application: Application): AndroidViewModel(application) {

    private val repository: AirStatusRepository = AirStatusRepository(application)
    private val allStatus: LiveData<List<AirStatus>> = repository.getAll()

    suspend fun getAllStatusFromWeb() {
        
        // Retrofit with coroutine will crash the app if there is no network
        try {
            repository.getAllFromWeb()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

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