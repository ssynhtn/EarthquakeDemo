package com.ssynhtn.earthquake.model.data

import com.google.gson.annotations.SerializedName


data class Geometry (
  @SerializedName("coordinates" ) var coordinates : ArrayList<Double> = arrayListOf()
)