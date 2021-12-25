package com.angelawang.airconditiondetector.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.angelawang.airconditiondetector.data.local.AirStatusDatabase
import com.angelawang.airconditiondetector.data.model.AirStatus
import com.angelawang.airconditiondetector.data.remote.Client
import kotlinx.coroutines.flow.Flow

class AirStatusRepository(application: Application) {

    private val airStatusDao = AirStatusDatabase.getInstance(application).airStatusDao()

    suspend fun getAllFromWeb() {
        val airStatusList = Client.getAirStatus()
        airStatusDao.insertAll(airStatusList)
    }

    fun getAll(): LiveData<List<AirStatus>> {
        return airStatusDao.getAll().asLiveData()
    }

    fun search(query: String): Flow<List<AirStatus>> {
        return airStatusDao.search(query)
    }
}