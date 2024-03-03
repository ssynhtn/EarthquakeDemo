package com.ssynhtn.earthquake.repo

import com.ssynhtn.earthquake.model.ui.Earthquake
import kotlinx.coroutines.delay

/**
 * wraps all earthquake related network requests
 */
class EarthquakeRepo {
    suspend fun fetch(): List<Earthquake> {
        val dataWrapper = Api.earthquakeService.fetchEarthquakes("2023-01-01", "2024-01-01")
        return dataWrapper.features.map { Earthquake.fromFeatures(it) }
    }

}