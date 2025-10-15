package com.example.weatherapplicationretrofit.models

import com.google.gson.annotations.SerializedName

data class RainData(
    @SerializedName("1h") val oneHour: String
)
