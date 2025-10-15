package com.example.weatherapplicationretrofit.api

import com.example.weatherapplicationretrofit.KEY
import com.example.weatherapplicationretrofit.UNITS_OF_MEASUREMENT
import com.example.weatherapplicationretrofit.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {
    @GET("weather")
    fun getWeather(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("appid") appid : String = KEY,
        @Query("units") units : String = UNITS_OF_MEASUREMENT,
    ) : Call<WeatherResponse>
}