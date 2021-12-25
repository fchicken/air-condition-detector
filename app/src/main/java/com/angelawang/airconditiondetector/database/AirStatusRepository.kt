package com.angelawang.airconditiondetector.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow

class AirStatusRepository(application: Application) {

    private val airStatusDao = AirStatusDatabase.getInstance(application).airStatusDao()

    fun getAll(): LiveData<List<AirStatus>> {
        return airStatusDao.getAll().asLiveData()
    }

    fun search(query: String): Flow<List<AirStatus>> {
        return airStatusDao.search(query)
    }
}