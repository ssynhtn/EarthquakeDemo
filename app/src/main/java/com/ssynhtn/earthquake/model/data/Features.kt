package com.ssynhtn.earthquake.model.data

import com.google.gson.annotations.SerializedName


data class Features(
  @SerializedName("id") var id: String = "",
  @SerializedName("properties") var properties: Properties = Properties(),
  @SerializedName("geometry") var geometry: Geometry = Geometry()
)