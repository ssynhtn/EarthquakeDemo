package com.ssynhtn.earthquake.repo

import com.ssynhtn.earthquake.model.ui.Earthquake

/**
 * wraps all earthquake related network requests
 */

interface EarthquakeRepo {
    suspend fun fetch(): List<Earthquake>

}

class RealEarthquakeRepo : EarthquakeRepo {
    override suspend fun fetch(): List<Earthquake> {
        val dataWrapper = Api.earthquakeService.fetchEarthquakes("2023-01-01", "2024-01-01")
        return dataWrapper.features.map { Earthquake.fromFeatures(it) }
    }

}