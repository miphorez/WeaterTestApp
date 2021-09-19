package com.miphorez.taskapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.miphorez.taskapplication.MainActivityStat
import com.miphorez.taskapplication.MainActivityViewModel
import com.miphorez.taskapplication.ui.screen.ScreenInputCity
import com.miphorez.taskapplication.ui.screen.ScreenShowWeather
import org.koin.androidx.compose.getViewModel

@Composable
fun ScreensByUiState() {
    val model = getViewModel<MainActivityViewModel>()

    val uiState by model.uiState.observeAsState(MainActivityStat.InputCityStat)

    Surface {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = uiState.titleRes)) },
                    navigationIcon = if (uiState == MainActivityStat.ShowWeatherStat) backIcon else null,
                    contentColor = Color.White
                )
            },
            content = {
                CurrentContent(model, uiState)
            }
        )
    }
}

private val backIcon: @Composable (() -> Unit)
    get() = {
        val model = getViewModel<MainActivityViewModel>()
        IconButton(onClick = {
            model.setNewScreenState(MainActivityStat.InputCityStat)
        }) {
            Icon(Icons.Filled.ArrowBack, null)
        }
    }

@Composable
private fun CurrentContent(model: MainActivityViewModel, stat: MainActivityStat) {

    val cityName by model.cityName.observeAsState()

    Column(
        modifier = Modifier
            .wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        when (stat) {
            MainActivityStat.InputCityStat -> ScreenInputCity(
                cityName = cityName ?: "",
                onValueChange = { inputText ->
                    model.setCityName(inputText)
                },
                onImeAction = { model.getWeatherForCity() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
            )

            MainActivityStat.ShowWeatherStat -> ScreenShowWeather(
                model = model,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
            )
        }
    }
}
