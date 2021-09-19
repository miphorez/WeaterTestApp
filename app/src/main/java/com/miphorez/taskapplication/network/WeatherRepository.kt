package com.miphorez.taskapplication.network

import com.miphorez.taskapplication.BuildConfig.API_KEY
import com.miphorez.taskapplication.model.WeatherResult
import com.miphorez.taskapplication.model.wrapperToWeatherResult
import com.miphorez.taskapplication.util.LCE

class WeatherRepository constructor(private val api: Api) {

    suspend fun getWeather(queryCity: String): LCE<WeatherResult> {
        return try {
            val apiResult = api.getWeather(
                queryCity = queryCity,
                queryLang = "ru",
                queryUnits = "metric",
                queryKey = API_KEY,
            )
            LCE.Success(data = wrapperToWeatherResult(apiResult))
        } catch (e: Exception) {
            println("getWeather: " + e.message.toString())
            LCE.Error(e)
        }
    }
}
