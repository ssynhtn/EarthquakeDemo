package com.ssynhtn.earthquake.repo

import com.ssynhtn.earthquake.model.data.EarthquakeData
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface EarthquakeService {
    @GET("fdsnws/event/1/query?format=geojson")
    suspend fun fetchEarthquakes(
        @Query("starttime") startTime: String,
        @Query("endtime") endTime: String,
        @Query("minmagnitude") minmagnitude: Int = 7
    ): EarthquakeData
}

object Api {
    val earthquakeService: EarthquakeService

    init {
        val builder = OkHttpClient.Builder()
        builder.connectionSpecs(listOf(
            ConnectionSpec.CLEARTEXT,
            ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .allEnabledTlsVersions()
                .allEnabledCipherSuites()
                .build()))
        val client = builder.build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://earthquake.usgs.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        earthquakeService = retrofit.create(EarthquakeService::class.java)
    }
}