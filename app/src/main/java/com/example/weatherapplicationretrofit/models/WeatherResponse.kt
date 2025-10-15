package com.example.weatherapplicationretrofit.models

data class WeatherResponse(
    val weather: List<WeatherData>,
    val main: TempData,
    val wind: WindData,
    val clouds: CloudsData,
    val rain: RainData?,
    val snow: SnowData?,
    val name: String,
)
