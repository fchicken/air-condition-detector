package com.angelawang.airconditiondetector.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "air_status_table")
data class AirStatus (
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "site_id")
    @SerializedName("SiteId")
    val siteId: String,
    @ColumnInfo(name = "site_name")
    @SerializedName("SiteName")
    val siteName: String? = null,
    @SerializedName("County")
    val county: String? = null,
    @SerializedName("PM2.5")
    val pm25: String? = null,
    @SerializedName("Status")
    val status: String? = null
)