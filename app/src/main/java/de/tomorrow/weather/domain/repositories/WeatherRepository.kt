package de.tomorrow.weather.domain.repositories

import de.tomorrow.weather.domain.weather.models.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(name: String, lat: Float, lon: Float): Flow<WeatherData>
}