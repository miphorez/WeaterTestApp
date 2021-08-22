package com.miphorez.taskapplication.model

import com.google.gson.annotations.SerializedName

data class WeatherRemote(
    @SerializedName("coord")
    val coord: Coordinate?,
    @SerializedName("weather")
    val weather: List<Weather?>?,
    @SerializedName("base")
    val base: String?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("wind")
    val wind: Wind?,
    @SerializedName("clouds")
    val clouds: Clouds?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("sys")
    val sys: Sys?,
    @SerializedName("timezone")
    val timezone: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("cod")
    val cod: Int?,
)

fun wrapperToWeatherResult(response: WeatherRemote?): WeatherResult {
    return WeatherResult(
        name = response?.name ?: "",
        country = response?.sys?.country ?: "",
        temp = response?.main?.temp?.toString() ?: "",
        feelsLike = response?.main?.feelsLike?.toString() ?: "",
        pressure = response?.main?.pressure?.toString() ?: "",
        humidity = response?.main?.humidity?.toString() ?: "",
        description = response?.weather?.map { it?.description }.toString(),
        image = response?.weather?.map { it?.icon }?.first().toString(),
        error = "",
    )
}

fun getEmptyWeatherResult(): WeatherResult {
    return WeatherResult(
        name = "",
        country = "",
        temp = "",
        feelsLike = "",
        pressure = "",
        humidity = "",
        description = "",
        image = "",
        error = "",
    )
}