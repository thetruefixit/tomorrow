package de.tomorrow.weather.presentation.screens.weather.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import de.tomorrow.weather.presentation.theme.BlueCatalina
import de.tomorrow.weather.presentation.theme.WhiteLilac
import java.math.BigDecimal
import java.math.RoundingMode

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
        )
        ScreenState.Loading -> LoadingScreen(padding = PaddingValues(8.dp))
    }
}

@Composable
private fun Screen(value: WeatherData) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(BlueCatalina, WhiteLilac),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationCard(weatherData = value)
            WeatherCard(currentWeather = value)
        }
    }
}

@Composable
private fun WeatherCard(currentWeather: WeatherData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DegreeView(temperature = currentWeather.temperature)
            Spacer(modifier = Modifier.height(8.dp))
            WindSpeedView(speed = currentWeather.windSpeed)
            WindDirectionView(direction = currentWeather.windDirection)
        }
    }
}

@Composable
private fun LocationCard(
    weatherData: WeatherData
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(color = Color.White, text = weatherData.name, fontSize = 32.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                color = Color.White,
                text = "${
                    weatherData.latitude.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                }, ${weatherData.longitude.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)}",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun DegreeView(temperature: Float) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            color = Color.White,
            text = BigDecimal.valueOf(temperature.toDouble()).setScale(0, RoundingMode.HALF_EVEN)
                .toString(),
            fontSize = 90.sp,
            maxLines = 1,
        )
        Text(
            color = Color.White,
            text = stringResource(id = R.string.degrees_symbol),
            fontSize = 28.sp,
            modifier = Modifier.padding(0.dp, 16.dp)
        )
        Text(
            color = Color.White,
            text = stringResource(id = R.string.abbreviation_celsius),
            fontSize = 24.sp,
            modifier = Modifier.padding(0.dp, 16.dp)
        )
    }
}

@Composable
private fun WindSpeedView(speed: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(color = Color.White, fontSize = 18.sp, text = stringResource(R.string.wind_speed))
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            color = Color.White,
            text = stringResource(
                id = R.string.abbreviation_speed_kmh,
                BigDecimal.valueOf(speed.toDouble()).setScale(0, RoundingMode.HALF_EVEN).toString()
            ),
            fontSize = 20.sp,
            maxLines = 1,
        )
    }
}

@Composable
private fun WindDirectionView(direction: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(color = Color.White, fontSize = 18.sp, text = stringResource(R.string.wind_direction))
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            color = Color.White,
            text = stringResource(id = R.string.abbreviation_wind_direction, direction),
            fontSize = 20.sp,
            maxLines = 1,
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun FullScreenPreview() {
    Screen(
        value = WeatherData(
            "Berlin", 54.10F, 10.15F, 80.0F, 10.1F, 1.1F, 1
        )
    )
}