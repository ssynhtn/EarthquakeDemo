package com.ssynhtn.earthquake.model.data

import com.google.gson.annotations.SerializedName


data class EarthquakeData(
    @SerializedName("features")
    val features: ArrayList<Features> = arrayListOf(),
)