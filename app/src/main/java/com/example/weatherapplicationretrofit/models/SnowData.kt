package com.example.weatherapplicationretrofit.models

import com.google.gson.annotations.SerializedName

data class SnowData(
    @SerializedName("1h") val oneHour: String
)
