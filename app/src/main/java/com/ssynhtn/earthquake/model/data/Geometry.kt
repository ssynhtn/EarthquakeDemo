package com.example.example

import com.google.gson.annotations.SerializedName


data class Geometry (
  @SerializedName("coordinates" ) var coordinates : ArrayList<Double> = arrayListOf()
)