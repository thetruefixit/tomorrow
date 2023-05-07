package de.tomorrow.weather.presentation.screens.weather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tomorrow.weather.R
import de.tomorrow.weather.core.ScreenState
import de.tomorrow.weather.domain.weather.models.WeatherData
import de.tomorrow.weather.presentation.core.ui.ErrorScreen
import de.tomorrow.weather.presentation.core.ui.LoadingScreen
import de.tomorrow.weather.presentation.screens.weather.CurrentWeatherViewModel
import kotlin.math.roundToInt

@Composable
fun CurrentWeatherScreen(
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val screenStateFlow = viewModel.screenState.collectAsStateWithLifecycle()
    when (val currentState = screenStateFlow.value) {
        is ScreenState.Content -> Screen(value = currentState.data)
        is ScreenState.Error -> ErrorScreen(
            errorText = stringResource(id = R.string.error_unknown),
            padding = PaddingValues(8.dp),
            true
        ) {}
        ScreenState.Loading -> LoadingScreen(padding = PaddingValues(8.dp))
    }
}

@Composable
private fun Screen(value: WeatherData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        LocationCard(weatherData = value)
        WeatherCard(currentWeather = value)
    }
}

@Composable
private fun WeatherCard(currentWeather: WeatherData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${currentWeather.temperature.roundToInt()}",
                    fontSize = 70.sp,
                    maxLines = 1
                )
                Text(
                    text = stringResource(id = R.string.degrees_symbol),
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxHeight()
                )
                Text(text = "C", fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = currentWeather.windSpeed.toString())
            Text(text = currentWeather.windDirection.toString())
        }
    }
}

@Composable
private fun LocationCard(
    weatherData: WeatherData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = weatherData.name, fontSize = 32.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${weatherData.latitude}, ${weatherData.longitude}", fontSize = 16.sp)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun FullScreenPreview() {
    Screen(
        value = WeatherData(
            "Berlin",
            54.10,
            10.15, 80.0, 10.1, 1.1,
            1
        )
    )
}