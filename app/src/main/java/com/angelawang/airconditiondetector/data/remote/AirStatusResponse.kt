package com.angelawang.airconditiondetector.data.remote

import com.angelawang.airconditiondetector.data.model.AirStatus

data class AirStatusResponse(
    val records: List<AirStatus>
)
