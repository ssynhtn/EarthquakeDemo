package com.ssynhtn.earthquake.model

data class Earthquake(
    val id: String = "",
    val time: Long = 0,
    val place: String = "",
    val magnitude: String = "",
    val coordinate: Coordinate = Coordinate()
)

data class Coordinate(
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val altitude: Double = 0.0
)