package com.ssynhtn.earthquake.repo

import com.ssynhtn.earthquake.model.data.EarthquakeData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface EarthquakeService {
//    https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2023-01-01&endtime=2024-01-01&minmagnitude=7
    @GET("fdsnws/event/1/query?format=geojson")
    suspend fun fetchEarthquakes(@Query("starttime") startTime: String, @Query("endtime") endTime: String, @Query("minmagnitude") minmagnitude: Int = 7): EarthquakeData
}

object Api {
    val earthquakeService: EarthquakeService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        earthquakeService = retrofit.create(EarthquakeService::class.java)
    }
}