package com.angelawang.airconditiondetector.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelawang.airconditiondetector.database.AirStatus
import com.angelawang.airconditiondetector.database.AirStatusDatabase
import com.angelawang.airconditiondetector.database.AirStatusRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val repository: AirStatusRepository = AirStatusRepository(application)
    private var allStatus: LiveData<List<AirStatus>> = repository.getAll()

    fun getAllStatus(): LiveData<List<AirStatus>> {
        return allStatus
    }

}