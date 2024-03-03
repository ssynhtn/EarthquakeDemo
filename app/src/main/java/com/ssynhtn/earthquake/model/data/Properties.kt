package com.ssynhtn.earthquake.model.data

import com.google.gson.annotations.SerializedName


data class Properties(
    @SerializedName("mag") var mag: String = "",
    @SerializedName("place") var place: String = "",
    @SerializedName("time") var time: Long = 0,
)