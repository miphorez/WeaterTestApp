package com.miphorez.taskapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.miphorez.taskapplication.MainActivityViewModel
import com.miphorez.taskapplication.R
import com.miphorez.taskapplication.model.WeatherResult
import com.miphorez.taskapplication.model.getEmptyWeatherResult

@Composable
fun ScreenShowWeather(
    model: MainActivityViewModel,
    modifier: Modifier = Modifier,
) {
    val weather by model.weather.observeAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .weight(0.7f),
            contentAlignment = Alignment.TopStart
        ) {
            ShowWeather(
                weather = weather ?: getEmptyWeatherResult(),
            )
        }
        Spacer(modifier = Modifier.weight(0.3f))
    }
}

@Composable
private fun ShowWeather(
    weather: WeatherResult,
) {
    if (weather.error.isEmpty() && weather.name.isNotEmpty()) {
        ShowWeatherFields(weather = weather)
    } else if (weather.error.isNotEmpty()) {
        ShowWeatherError(weather = weather)
    } else {
        ShowWeatherWait()
    }
}

@Composable
private fun ShowWeatherWait() {
    Column {
        Text(
            text = stringResource(id = R.string.wait_tittle),
            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Left),
            color = Color.Gray
        )
    }
}

@Composable
private fun ShowWeatherError(
    weather: WeatherResult,
) {
    Column {
        Text(
            text = stringResource(id = R.string.error_tittle),
            style = MaterialTheme.typography.h5.copy(textAlign = TextAlign.Left),
            color = Color.Black
        )
        Text(
            text = weather.error,
            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Left),
            color = Color.Black
        )
    }
}

@Composable
private fun ShowWeatherFields(
    weather: WeatherResult,
) {
    Column {
        Text(
            text = stringResource(id = R.string.weather_tittle),
            style = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Left),
            color = Color.Black
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "${weather.name} (${weather.country})",
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = stringResource(id = R.string.weather_temp, weather.temp),
        )
        Text(
            text = stringResource(id = R.string.weather_feelsLike, weather.feelsLike),
        )
        Text(
            text = stringResource(id = R.string.weather_pressure, weather.pressure),
        )
        Text(
            text = stringResource(id = R.string.weather_humidity, weather.humidity) + "%",
        )

        Text(
            text = if (weather.description.length > 2) {
                weather.description.substring(1, weather.description.length - 1)
                    .replaceFirstChar { it.uppercase() }
            } else "",
        )
    }
}