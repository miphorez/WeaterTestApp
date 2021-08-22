package com.miphorez.taskapplication

import android.content.Context
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miphorez.taskapplication.model.WeatherResult
import com.miphorez.taskapplication.model.getEmptyWeatherResult
import com.miphorez.taskapplication.network.WeatherRepository
import com.miphorez.taskapplication.util.LCE
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(
    private val repository: WeatherRepository,
    @ApplicationContext val context: Context
) : ViewModel() {
    private val _uiState = MutableLiveData<MainActivityStat>(MainActivityStat.InputCityStat)
    val uiState: LiveData<MainActivityStat> = _uiState

    private val _weather = MutableLiveData<WeatherResult>()
    val weather: LiveData<WeatherResult> = _weather

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    init {
        _uiState.value = MainActivityStat.InputCityStat
    }

    fun setNewActivityStat(status: StatusEnum) {
        when (status) {
            StatusEnum.INPUT_CITY -> _uiState.value = MainActivityStat.InputCityStat
            StatusEnum.SHOW_WEATHER -> _uiState.value = MainActivityStat.ShowWeatherStat
        }
    }

    private fun getWeather() {
        if (_cityName.value == null || _cityName.value!!.isEmpty()) {
            val error = getEmptyWeatherResult()
            error.error = context.getString(R.string.empty_city)
            _weather.value = error
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = repository.getWeather(cityName.value ?: "")) {
                    is LCE.Success -> {
                        withContext(Dispatchers.Main) {
                            _weather.value = result.data
                        }
                    }
                    is LCE.Error -> {
                        withContext(Dispatchers.Main) {
                            val error = getEmptyWeatherResult()
                            error.name = cityName.value ?: ""
                            error.error = result.message
                            _weather.value = error
                        }
                    }
                }
            }
        }
    }

    fun setCityName(inputText: String) {
        _cityName.value = inputText
    }

    fun getWeatherForCity() {
        setNewScreenState(MainActivityStat.ShowWeatherStat)
        getWeather()
    }

    fun setNewScreenState(state: MainActivityStat) {
        if (state == MainActivityStat.InputCityStat) {
            _cityName.value = ""
            _weather.value = getEmptyWeatherResult()
        }
        _uiState.value = state
    }
}
