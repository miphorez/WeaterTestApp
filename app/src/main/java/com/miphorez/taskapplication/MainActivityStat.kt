package com.miphorez.taskapplication

sealed class MainActivityStat(val titleRes: Int) {
    object InputCityStat: MainActivityStat(R.string.input_city)
    object ShowWeatherStat: MainActivityStat(R.string.show_weather)
}
