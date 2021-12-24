package com.angelawang.airconditiondetector.database

import android.app.Application
import androidx.lifecycle.LiveData

class AirStatusRepository(application: Application) {

    private val airStatusDao = AirStatusDatabase.getInstance(application).airStatusDao()

    fun getAll(): LiveData<List<AirStatus>> {
        return airStatusDao.getAll()
    }

    fun search(query: String): LiveData<List<AirStatus>> {
        return airStatusDao.search(query)
    }
}