package com.ssynhtn.earthquake.model.ui

import com.example.example.Geometry
import com.ssynhtn.earthquake.model.data.Features

data class Earthquake(
    val id: String = "",
    val time: Long = 0,
    val place: String = "",
    val magnitude: Double = 0.0,
    val coordinate: Coordinate = Coordinate()
) {

    val strong: Boolean
        get() = magnitude >= 7.5

    companion object {
        /**
         * convert from server data model to ui data model
         */
        fun fromFeatures(features: Features): Earthquake {
            val coordinate = Coordinate.fromGeometry(features.geometry)
            return Earthquake(
                features.id,
                features.properties.time,
                features.properties.place,
                features.properties.mag,
                coordinate
            )
        }
    }
}

data class Coordinate(
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val altitude: Double = 0.0
) {

    companion object {
        fun fromGeometry(geometry: Geometry): Coordinate {
            val coordinates = geometry.coordinates
            val longitude = if (coordinates.size >= 1) coordinates[0] else 0.0
            val latitude = if (coordinates.size >= 2) coordinates[1] else 0.0
            val altitude = if (coordinates.size >= 3) coordinates[2] else 0.0
            return Coordinate(longitude, latitude, altitude)
        }
    }
}