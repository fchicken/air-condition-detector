package com.angelawang.airconditiondetector.data.local

import androidx.room.*
import com.angelawang.airconditiondetector.data.model.AirStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface AirStatusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(airStatus: AirStatus)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(airStatusList: List<AirStatus>)

    @Query("SELECT * FROM `air_status_table`")
    fun getAll(): Flow<List<AirStatus>>

    @Query("SELECT * FROM `air_status_table` WHERE `site_name` LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<AirStatus>>


}