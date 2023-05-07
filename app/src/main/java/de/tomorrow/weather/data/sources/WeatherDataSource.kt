package de.tomorrow.weather.data.sources

import de.tomorrow.weather.data.models.WeatherDataDto
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {
    fun getWeather(lat: Float, lon: Float): Flow<WeatherDataDto>
}