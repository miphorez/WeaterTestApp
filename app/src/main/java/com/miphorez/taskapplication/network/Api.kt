package com.miphorez.taskapplication.network

import com.miphorez.taskapplication.model.WeatherRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") queryCity: String,
        @Query("lang") queryLang: String,
        @Query("units") queryUnits: String,
        @Query("appid") queryKey: String,
    ): WeatherRemote

}