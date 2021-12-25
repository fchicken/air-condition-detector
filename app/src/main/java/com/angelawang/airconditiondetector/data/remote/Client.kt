package com.angelawang.airconditiondetector.data.remote

import com.angelawang.airconditiondetector.data.model.AirStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    private const val URL = "https://data.epa.gov.tw/api/v1/"

    private val apiService: ApiService

    init {
        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getAirStatus() : List<AirStatus> = withContext(Dispatchers.IO) {
        val response = apiService.getAirStatus()
        if (response.isSuccessful) {
            val body = response.body()!!
            return@withContext body.records
        } else {
            throw Exception(response.errorBody()?.charStream()?.readText())
        }
    }
}