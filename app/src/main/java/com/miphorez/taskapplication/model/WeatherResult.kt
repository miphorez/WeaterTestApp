package com.miphorez.taskapplication.model

data class WeatherResult(
    var name: String,
    val country: String,
    val temp: String,
    val feelsLike: String,
    val pressure: String,
    val humidity: String,
    val description: String,
    val image: String,
    var error: String = "",
)
