package com.angelawang.airconditiondetector.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "air_status_table")
data class AirStatus (
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "site_id")
    val siteId: String,
    @ColumnInfo(name = "site_name")
    val siteName: String? = null,
    val county: String? = null,
    val pm25: String? = null,
    val status: String? = null
)