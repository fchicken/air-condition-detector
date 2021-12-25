package com.angelawang.airconditiondetector.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("aqx_p_432?limit=1000&api_key=9be7b239-557b-4c10-9775-78cadfc555e9&sort=ImportDate%20desc&format=json")
    suspend fun getAirStatus() : Response<AirStatusResponse>
}