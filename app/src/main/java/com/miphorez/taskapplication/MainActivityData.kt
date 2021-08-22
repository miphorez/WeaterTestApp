package com.miphorez.taskapplication

enum class StatusEnum(val titleRes: Int) {
    INPUT_CITY(R.string.input_city),
    SHOW_WEATHER(R.string.show_weather);

}

fun getUiStatByActivityState(stat: MainActivityStat?): StatusEnum {
    return when (stat) {
        MainActivityStat.InputCityStat -> StatusEnum.INPUT_CITY
        MainActivityStat.ShowWeatherStat -> StatusEnum.SHOW_WEATHER
        else -> StatusEnum.INPUT_CITY
    }
}
