package com.ssynhtn.earthquake.model.data

import com.google.gson.annotations.SerializedName


data class Properties(
    @SerializedName("mag") var mag: Double = 0.0,
    @SerializedName("place") var place: String = "",
    @SerializedName("time") var time: Long = 0,
)