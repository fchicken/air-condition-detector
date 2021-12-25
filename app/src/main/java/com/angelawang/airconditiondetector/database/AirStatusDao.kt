package com.angelawang.airconditiondetector.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AirStatusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(airStatus: AirStatus)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(airStatusList: List<AirStatus>)

    @Query("SELECT * FROM `air_status_table`")
    fun getAll(): Flow<List<AirStatus>>

    @Query("SELECT * FROM `air_status_table` WHERE `site_name` LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<AirStatus>>


}