package com.example.example

import com.google.gson.annotations.SerializedName
import com.ssynhtn.earthquake.model.Coordinate
import java.io.Serializable


data class Geometry (
  @SerializedName("coordinates" ) var coordinates : ArrayList<Double> = arrayListOf()
) {
  fun toCoordinate(): Coordinate {
    val longitude = if (coordinates.size >= 1) coordinates[0] else 0.0
    val latitude = if (coordinates.size >= 2) coordinates[1] else 0.0
    val altitude = if (coordinates.size >= 3) coordinates[2] else 0.0
    return Coordinate(longitude, latitude, altitude)
  }
}