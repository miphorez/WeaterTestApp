package com.miphorez.taskapplication

sealed class MainActivityStat {
    object InputCityStat: MainActivityStat()
    object ShowWeatherStat: MainActivityStat()
}
